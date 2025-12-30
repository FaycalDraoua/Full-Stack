
import UserProfile from "./UserProfile.jsx";
import {useState, useEffect} from "react";
import { Button, ButtonGroup } from "@chakra-ui/react"

const users = [
    {
        name: "Imane",
        age: 28,
        gender: "FEMALE"
    },
    {
        name: "Bilal",
        age: 32,
        gender: "Male"
    },
    {
        name: "Manel",
        age: 20,
        gender: "FEMALE"
    },
    {
        name: "Nasro",
        age: 24,
        gender: "Male"
    },
]

const UserProfiles = ({users}) => (
    <div>
        {users.map((user, index) =>(
        <UserProfile
            key = {index}
            name={user.name}
            age={user.age}
            gender={user.gender}
            userNumber={index}
        />
        ))}
    </div>
    )


function App() {

  const brand = "MyBrand"

  const [counter, setCounter] = useState(0)

  const [isLoading, setIsLoading] = useState(true)


    useEffect(() => {
        setTimeout(() => {
            setIsLoading(() => false)},
            4000)}
        , []);


    if(isLoading){
        return <h1>Loading...</h1>
    }
    return(
    <div>

        <button onClick={() => setCounter(prevCount => prevCount + 1)}>
            Increment counter
        </button>
        <h1>Counter : {counter}</h1>

      <ol>
          <UserProfile
              name={"Alice"}
              age={28}
              gender={"women"}
              userNumber={10}
          >
              <p>Hello</p>
              <p>Hello2</p>
              <p>Hello3</p>

          </UserProfile>

          <UserProfile
              name = "Bob"
              age={30}
              gender="men"
              userNumber={70}
          >

          </UserProfile>

          <li>
              <UserProfiles users={users}/>
          </li>
      </ol>
    </div>
    )
}

export default App
