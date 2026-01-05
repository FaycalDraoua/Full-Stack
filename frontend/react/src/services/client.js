import axios from 'axios';

// async : On déclare la fonction comme "asynchrone". Cela veut dire :
// "Attention, cette fonction va prendre du temps (le temps du voyage réseau), elle ne donne pas le résultat immédiatement."
export const getCustomers = async () => {
   //await axios.get(...) : C'est le cœur de l'appel. await dit à JavaScript : "Attends que le serveur réponde avant de continuer".
    // Axios va chercher les données sur l'URL construite.
    try {
        // 1. Axios fait la requête et attend la réponse
        const response = await axios.get(`${import.meta.env.VITE_API_BASE_URL}/api/v1/customers`);

        // 2. Axios a "intercepté" le JSON et l'a mis dans l'objet 'response'
        // 'response' contient : data (le JSON parsé), status (200), headers, etc.

        return response; // 3. Tu retournes l'objet complet au composant App.jsx
    } catch (e) {
        throw e;
    }
}