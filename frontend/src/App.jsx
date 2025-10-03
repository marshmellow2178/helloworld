import { useEffect, useState } from 'react'
import './App.css'

function App() {

  const [input, setInput] = useState("");
  const [user, setUser] = useState("");
  const [users, setUsers] = useState([]);
  const [detail, setDetail] = useState([]);

  useEffect(()=>{
    fetch(`/api/users`)
    .then((res)=>res.json())
    .then((data)=>{
      setUsers(data);
    })
    .catch((err)=>console.log(err))
  },[user]);

  function handleSubmit(event){
    event.preventDefault();
    fetch(`/api/users/new`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(input)
    })
    .then(res=>res.json())
    .then(data=>{
      setDetail(data);
      setUser(data.name);
      event.target[0].value = ""; //input 값 초기화  
    })
  }

  function handleInput(event){
    setInput(event.target.value);
  }

  function handleDelete(id){
    fetch(`/api/users/${id}`,{method: "DELETE"})
    .then(res=>{setUser("");})
    .catch(err=>console.log(err));
  }

  function handleDetail(id){
    fetch(`/api/users/${id}`)
    .then(res=>res.json())
    .then(data=>{
      setDetail(data);
    })
    .catch(err=>console.log(err));
  }

  function handleUpdate(id){
    fetch(`/api/users/${id}`, {
      method: "PATCH", 
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(input)})
    .then(res=>{
        setUser(input);
        handleDetail(id);
    })
  }

  return (
    <>
      {(input == "") ? <p>Enter username</p> : <p>Hello, {user}!</p>}
      
      <form onSubmit={handleSubmit}>
        <input type='text'
        onInput={handleInput}
        required
        placeholder='username' />
        <button type='submit'>
          submit
        </button>
      </form>
      
      <p>{users.length} users submitted</p>
      <ul>
        {users.map((value)=>{
          return <li key={value.id}>
            {value.name}
            <button type='button'
            onClick={()=>handleDetail(value.id)}>DETAILS</button>
            <button type='button'
            onClick={()=>handleDelete(value.id)}>X</button>
          </li>
        })}
      </ul>

      <div>
        <b>NAME: {detail.name}</b>
        <p>ID: {detail.id}</p>
        <button type='button'
        onClick={()=>handleUpdate(detail.id)}>UPDATE</button>
      </div>
    </>
  )
}

export default App
