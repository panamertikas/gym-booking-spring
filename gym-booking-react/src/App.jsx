import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import Login from './pages/Login'
import Dashboard from './pages/Dashboard'
import MyBookings from './pages/MyBookings'
import Profile from './pages/Profile'
import Members from './pages/admin/Members'
import GymClasses from './pages/admin/GymClasses'
import Bookings from './pages/admin/Bookings'
import RegisterMember from './pages/admin/RegisterMember'

function App() {
  const role = localStorage.getItem('role')
  const token = localStorage.getItem('token')

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/dashboard" element={token ? <Dashboard /> : <Navigate to="/" />} />
        <Route path="/my-bookings" element={token ? <MyBookings /> : <Navigate to="/" />} />
        <Route path="/profile" element={token ? <Profile /> : <Navigate to="/" />} />
        <Route path="/admin/members" element={token ? <Members /> : <Navigate to="/" />} />
        <Route path="/admin/gym-classes" element={token ? <GymClasses /> : <Navigate to="/" />} />
        <Route path="/admin/bookings" element={token ? <Bookings /> : <Navigate to="/" />} />
        <Route path="/admin/register-member" element={token ? <RegisterMember /> : <Navigate to="/" />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App