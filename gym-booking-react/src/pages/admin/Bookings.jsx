import { useState, useEffect } from 'react'
import Navbar from '../../components/Navbar'

function Bookings() {
  const [bookings, setBookings] = useState([])
  const token = localStorage.getItem('token')
  const role = localStorage.getItem('role')

  useEffect(() => {
    loadBookings()
  }, [])

  async function loadBookings() {
    const response = await fetch('/api/bookings', {
      headers: { 'Authorization': 'Bearer ' + token }
    })
    const data = await response.json()
    setBookings(data)
  }

  async function deleteBooking(id) {
    if (!confirm('Are you sure?')) return
    const response = await fetch(`/api/bookings/${id}`, {
      method: 'DELETE',
      headers: { 'Authorization': 'Bearer ' + token }
    })
    if (response.ok) {
      loadBookings()
    } else {
      const msg = await response.text()
      alert(msg)
    }
  }

  return (
    <div>
      <Navbar role={role} />
      <div style={styles.container}>
        <h1>Bookings</h1>
        <table style={styles.table}>
          <thead>
            <tr>
              <th style={styles.th}>ID</th>
              <th style={styles.th}>Date</th>
              <th style={styles.th}>Time</th>
              <th style={styles.th}>Gym Class</th>
              <th style={styles.th}>Member</th>
              <th style={styles.th}>Actions</th>
            </tr>
          </thead>
          <tbody>
            {bookings.map(booking => (
              <tr key={booking.id}>
                <td style={styles.td}>{booking.id}</td>
                <td style={styles.td}>{booking.date}</td>
                <td style={styles.td}>{booking.time}</td>
                <td style={styles.td}>{booking.gymClass.className}</td>
                <td style={styles.td}>{booking.member.firstname} {booking.member.lastname}</td>
                <td style={styles.td}>
                  <button style={styles.deleteBtn} onClick={() => deleteBooking(booking.id)}>Delete</button>
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

export default Bookings