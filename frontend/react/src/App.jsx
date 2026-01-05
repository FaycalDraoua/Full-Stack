import { Button } from '@chakra-ui/react'
import Index from "./shared/SideBar.jsx";
import {useEffect, useState} from "react";
import {getCustomers} from "./services/client.js";
import SideBar from "./shared/SideBar.jsx";
import { Spinner } from '@chakra-ui/react'

function App() {

    const [customers, setCustomers] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true)
        getCustomers().then(res => setCustomers(res.data))
            .catch(err => console.error(err))
            .finally(() => setTimeout(() => setLoading(false), 4000));
    }, []);

    if(loading){
        console.log("No customers found");
        return(
            <SideBar>
                <Spinner size='xl'/>
            </SideBar>
        )
    }

    return(
    <SideBar>
        <Button> Click on me </Button>
        {customers.map((customer,index)=>(
            <p>{customer.name}</p>
        ))}
    </SideBar>
    )
}

export default App
