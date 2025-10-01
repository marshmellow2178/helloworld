import { useEffect, useState } from 'react'
import './App.css'

function App() {

  const [input, setInput] = useState("");
  const [user, setUser] = useState("anon");
  const [users, setUsers] = useState([]);

  useEffect(()=>{
    fetch(`/users`)
    .then((res)=>res.json())
    .then((data)=>{
      setUsers(data);
    })
    .catch((err)=>console.log(err))
  },[user]);

  function handleSubmit(event){
    event.preventDefault();
    setUser(input);
  }

  function handleInput(event){
    setInput(event.target.value);
  }

  return (
    <>
      <form onSubmit={handleSubmit}>
        <input type='text'
        onInput={handleInput}
        placeholder='username' />
        <button type='submit'>
          submit
        </button>
      </form>
      <p>Hello, {user}!</p>

      <ul>
        {users.map((value)=>{
          return <li key={value.id}>
            {value.name}
          </li>
        })}
      </ul>
    </>
  )
}

export default App
