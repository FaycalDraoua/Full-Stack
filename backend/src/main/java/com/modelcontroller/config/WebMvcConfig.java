package com.modelcontroller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

///@Configuration : Dit à Spring que ce fichier contient des réglages (des "Beans") qu'il doit lire dès le démarrage de l'application.
/// Sans ça, ce code ne serait jamais exécuté.
@Configuration
/// WebMvcConfigurer :  C'est une interface qui donne accès à toutes les options de configuration du Web (les routes, les ressources statiques, et surtout le CORS).
public class WebMvcConfig implements WebMvcConfigurer {

    /*
    * @Value : Va chercher la valeur de "cors.allowed-origins" dans le fichier application.yml (ou .properties).
    * ${cors.allowed-origins} : contient Les origines(URL) autorisées pour le CORS, séparées par des virgules. ou * pour tout autoriser.
        ps: 1. CROS : cest le mécanisme qui permet au navigateur d'autoriser (ou non) des requêtes HTTP faites depuis une origine différente de celle de la page.
            2. Une origine = schéma + hôte + port (ex: https://example.com:443). Si l'un change, l'origine change.
    * .split(',') : C'est une expression SpEL (Spring Expression Language). Si dans ton .yml tu as écrit http://localhost:5173,http://mon-site.com, Spring va couper la phrase à chaque virgule pour créer une liste des URL au lieu d'une seule phrase.
    *  private List<String> allowedOrigins : On stocke ces URL dans une liste d'origines autorisées.
    * */
    @Value("#{'${cors.allowed-origins}'.split(',')}")
    private List<String> allowedOrigins;

    ///  Même principe que pour les origines, mais là c'est pour les méthodes HTTP autorisées (GET, POST, PUT, DELETE, etc.).
    @Value("#{'${cors.allowed-methods}'.split(',')}")
    private List<String> allowedMethods;

    /// On redéfinit la méthode addCorsMappings pour personnaliser les règles CORS.
    @Override
    /// CorsRegistry registry : C'est l'outil que Spring nous donne pour enregistrer nos règles de sécurité.
    public void addCorsMappings(CorsRegistry registry) {
        /// addMapping("/api/**") : C'est le périmètre de sécurité. On dit : "Ces règles s'appliquent à toutes les URLs qui commencent par /api/". Le ** signifie "et tout ce qui suit".
        CorsRegistration corsRegistration = registry.addMapping("/api/**");
        allowedOrigins.forEach(corsRegistration::allowedOrigins);
        allowedMethods.forEach(corsRegistration::allowedMethods);

        /**.forEach(...) : On parcourt la liste des origines (ex: localhost:5173) et des méthodes (ex: GET, POST) qu'on a récupérées dans le .yml.

        *  .corsRegistration::allowedOrigins : Pour chaque origine trouvée, on dit au registre : "Celle-là a le droit d'entrer".

          .allowedMethods : On fait pareil pour les verbes HTTP. Si POST n'est pas dans ta liste, ton frontend pourra lire les clients (GET) mais ne pourra pas en créer de nouveaux.


        PS : explication de comment on a pu utliser registry sans injecter son bean avec @Autowired :

         1. Au démarrage, Spring fait le tour de toutes les classes annotées avec @Configuration. Lorsqu'il voit que ta classe implémente WebMvcConfigurer, il se dit : "Tiens, cette classe veut configurer quelque chose. Je vais l'appeler au bon moment."

         2. L'injection par les paramètres (Inversion de Contrôle)
         Ce n'est pas toi qui crées le CorsRegistry, c'est Spring qui le crée et te le donne "en main propre" via l'argument de la méthode :

         @Override
         public void addCorsMappings(CorsRegistry registry) { // <--- Spring injecte l'objet ici
         // Tu utilises l'objet que Spring t'a fourni
         }

         C'est ce qu'on appelle l'Inversion de Contrôle (IoC) :
         Sans IoC : Tu devrais faire CorsRegistry registry = new CorsRegistry(); (et ça ne marcherait pas car il ne serait pas relié au système de Spring).
         Avec IoC : Spring possède l'objet registry, il connaît toute la tuyauterie interne, et il te le passe simplement pour que tu puisses y ajouter tes règles.

         3. Pourquoi pas @Autowired ?
         On utilise @Autowired pour récupérer un Bean (un objet) que l'on veut utiliser dans nos méthodes. Ici, c'est l'inverse :
         La méthode addCorsMappings est une méthode de configuration.
         Spring appelle cette méthode une seule fois au démarrage.
         Il te fournit les outils (registry) dont tu as besoin uniquement pendant cette seconde-là.
         **/
    }
}
