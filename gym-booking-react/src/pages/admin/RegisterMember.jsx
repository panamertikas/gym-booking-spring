import { useState } from 'react'
import Navbar from '../../components/Navbar'

function RegisterMember() {
    const [firstname, setFirstname] = useState('')
    const [lastname, setLastname] = useState('')
    const [age, setAge] = useState('')
    const [mail, setMail] = useState('')
    const [membershipType, setMembershipType] = useState('MONTHLY')
    const token = localStorage.getItem('token')
    const role = localStorage.getItem('role')

    async function registerMember() {
        const response = await fetch('/api/members', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify({ firstname, lastname, age: parseInt(age), mail, membershipType })
        })
        if (response.ok) {
            setFirstname('')
            setLastname('')
            setAge('')
            setMail('')
            alert('Member registered successfully!')
        } else {
            const msg = await response.text()
            alert(msg)
        }
    }

    return (
        <div>
            <Navbar role={role} />
            <div style={styles.container}>
                <h1>Register New Member</h1>
                <input style={styles.input} placeholder="Firstname" value={firstname} onChange={e => setFirstname(e.target.value)} />
                <input style={styles.input} placeholder="Lastname" value={lastname} onChange={e => setLastname(e.target.value)} />
                <input style={styles.input} placeholder="Age" type="number" value={age} onChange={e => setAge(e.target.value)} />
                <input style={styles.input} placeholder="Email" value={mail} onChange={e => setMail(e.target.value)} />
                <select style={styles.input} value={membershipType} onChange={e => setMembershipType(e.target.value)}>
                    <option value="DAILY">Daily</option>
                    <option value="MONTHLY">Monthly</option>
                    <option value="YEARLY">Yearly</option>
                </select>
                <button style={styles.btn} onClick={registerMember}>Register Member</button>
            </div>
        </div>
    )
}

const styles = {
    container: {
        maxWidth: '500px',
        margin: '30px auto',
        padding: '20px',
        backgroundColor: 'white',
        borderRadius: '8px',
        boxShadow: '0 2px 5px rgba(0,0,0,0.1)'
    },
    input: {
        display: 'block',
        width: '100%',
        padding: '10px',
        marginBottom: '10px',
        border: '1px solid #ddd',
        borderRadius: '4px',
        boxSizing: 'border-box'
    },
    btn: {
        width: '100%',
        padding: '10px',
        backgroundColor: '#333',
        color: 'white',
        border: 'none',
        borderRadius: '4px',
        cursor: 'pointer'
    }
}

export default RegisterMember