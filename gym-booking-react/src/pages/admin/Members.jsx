import { useState, useEffect } from 'react'
import Navbar from '../../components/Navbar'

function Members() {
  const [members, setMembers] = useState([])
  const token = localStorage.getItem('token')
  const role = localStorage.getItem('role')

  useEffect(() => {
    loadMembers()
  }, [])

  async function loadMembers() {
    const response = await fetch('/api/members', {
      headers: { 'Authorization': 'Bearer ' + token }
    })
    const data = await response.json()
    setMembers(data)
  }

  async function deleteMember(id) {
    if (!confirm('Are you sure?')) return
    const response = await fetch(`/api/members/${id}`, {
      method: 'DELETE',
      headers: { 'Authorization': 'Bearer ' + token }
    })
    if (response.ok) {
      loadMembers()
    } else {
      const msg = await response.text()
      alert(msg)
    }
  }

  return (
    <div>
      <Navbar role={role} />
      <div style={styles.container}>
        <h1>Members</h1>
        <table style={styles.table}>
          <thead>
            <tr>
              <th style={styles.th}>ID</th>
              <th style={styles.th}>Firstname</th>
              <th style={styles.th}>Lastname</th>
              <th style={styles.th}>Email</th>
              <th style={styles.th}>Membership</th>
              <th style={styles.th}>Actions</th>
            </tr>
          </thead>
          <tbody>
            {members.map(member => (
              <tr key={member.id}>
                <td style={styles.td}>{member.id}</td>
                <td style={styles.td}>{member.firstname}</td>
                <td style={styles.td}>{member.lastname}</td>
                <td style={styles.td}>{member.mail}</td>
                <td style={styles.td}>{member.membershipType || 'N/A'}</td>
                <td style={styles.td}>
                  <button style={styles.deleteBtn} onClick={() => deleteMember(member.id)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}

const styles = {
  container: {
    maxWidth: '900px',
    margin: '30px auto',
    padding: '20px',
    backgroundColor: 'white',
    borderRadius: '8px',
    boxShadow: '0 2px 5px rgba(0,0,0,0.1)'
  },
  table: { width: '100%', borderCollapse: 'collapse' },
  th: { padding: '10px', backgroundColor: '#333', color: 'white', textAlign: 'left' },
  td: { padding: '10px', border: '1px solid #ddd' },
  deleteBtn: { padding: '6px 12px', backgroundColor: '#dc3545', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }
}

export default Members