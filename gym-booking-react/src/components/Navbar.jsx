import { Link, useNavigate } from 'react-router-dom'

function Navbar({ role }) {
  const navigate = useNavigate()

  function logout() {
    localStorage.removeItem('token')
    localStorage.removeItem('role')
    navigate('/login')
  }

  if (role === 'ADMIN') {
    return (
      <nav style={styles.nav}>
        <Link style={styles.link} to="/admin/members">Members</Link>
        <Link style={styles.link} to="/admin/gym-classes">Gym Classes</Link>
        <Link style={styles.link} to="/admin/bookings">Bookings</Link>
        <Link style={styles.link} to="/admin/register-member">Register Member</Link>
        <a style={styles.link} href="#" onClick={logout}>Logout</a>
      </nav>
    )
  }

  return (
    <nav style={styles.nav}>
      <Link style={styles.link} to="/dashboard">My Dashboard</Link>
      <Link style={styles.link} to="/my-bookings">My Bookings</Link>
      <Link style={styles.link} to="/profile">My Profile</Link>
      <a style={styles.link} href="#" onClick={logout}>Logout</a>
    </nav>
  )
}

const styles = {
  nav: {
    backgroundColor: '#333',
    padding: '15px',
  },
  link: {
    color: 'white',
    textDecoration: 'none',
    marginRight: '20px',
    fontSize: '16px',
  }
}

export default Navbar