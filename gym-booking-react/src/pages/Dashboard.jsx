import { useState, useEffect } from 'react'
import DatePicker from 'react-datepicker'
import 'react-datepicker/dist/react-datepicker.css'
import Navbar from '../components/Navbar'

function Dashboard() {
    const [gymClasses, setGymClasses] = useState([])
    const [selectedClass, setSelectedClass] = useState(null)
    const [selectedDate, setSelectedDate] = useState(null)
    const [selectedTime, setSelectedTime] = useState('10:00')
    const [showModal, setShowModal] = useState(false)
    const [message, setMessage] = useState('')
    const [isError, setIsError] = useState(false)
    const token = localStorage.getItem('token')
    const role = localStorage.getItem('role')

    useEffect(() => {
        loadGymClasses()
    }, [])

    async function loadGymClasses() {
        const response = await fetch('/api/gym_classes', {
            headers: { 'Authorization': 'Bearer ' + token }
        })
        const data = await response.json()
        setGymClasses(data)
    }

    async function getMyMemberId() {
        const response = await fetch('/api/members/me', {
            headers: { 'Authorization': 'Bearer ' + token }
        })
        if (response.ok) {
            const member = await response.json()
            return member.id
        }
        return null
    }

    async function handleDateSelect(date) {
        setSelectedDate(date)
        const dateStr = date.toISOString().split('T')[0]
        const response = await fetch(`/api/bookings/availability/${selectedClass.id}/${dateStr}`, {
            headers: { 'Authorization': 'Bearer ' + token }
        })
        const available = await response.json()
        if (!available) {
            showMsg('This class is full on ' + dateStr + '!', true)
            return
        }
        setShowModal(true)
    }

    async function confirmBooking() {
        const dateStr = selectedDate.toISOString().split('T')[0]
        const memberId = await getMyMemberId()
        if (!memberId) {
            showMsg('Could not find your member profile!', true)
            return
        }
        const response = await fetch('/api/bookings', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify({
                date: dateStr,
                time: selectedTime,
                gymClassId: selectedClass.id,
                memberId
            })
        })
        setShowModal(false)
        if (response.ok) {
            showMsg('Booking successful for ' + dateStr + ' at ' + selectedTime + '!', false)
            loadGymClasses()
        } else {
            const msg = await response.text()
            showMsg(msg, true)
        }
    }

    function showMsg(msg, error) {
        setMessage(msg)
        setIsError(error)
        setTimeout(() => setMessage(''), 3000)
    }

    return (
        <div>
            <Navbar role={role} />
            <div style={styles.container}>
                <h1>Available Classes</h1>
                {gymClasses.map(gymClass => (
                    <div
                        key={gymClass.id}
                        style={{
                            ...styles.card,
                            borderColor: selectedClass?.id === gymClass.id ? '#333' : '#ddd'
                        }}
                        onClick={() => setSelectedClass(gymClass)}
                    >
                        <strong>{gymClass.className}</strong> — {gymClass.trainer}
                        <span style={{
                            ...styles.badge,
                            backgroundColor: gymClass.currentCapacity >= gymClass.maxCapacity ? '#dc3545' : '#28a745'
                        }}>
                            {gymClass.currentCapacity}/{gymClass.maxCapacity} {gymClass.currentCapacity >= gymClass.maxCapacity ? 'FULL' : 'Available'}
                        </span>
                    </div>
                ))}

                {selectedClass && (
                    <div style={styles.calendarSection}>
                        <h2>Book: {selectedClass.className}</h2>
                        <p>Select a date:</p>
                        <DatePicker
                            selected={selectedDate}
                            onChange={handleDateSelect}
                            minDate={new Date()}
                            inline
                        />
                    </div>
                )}

                {message && (
                    <div style={{ ...styles.message, backgroundColor: isError ? '#f8d7da' : '#d4edda', color: isError ? '#721c24' : '#155724' }}>
                        {message}
                    </div>
                )}
            </div>

            {showModal && (
                <>
                    <div style={styles.overlay} onClick={() => setShowModal(false)} />
                    <div style={styles.modal}>
                        <h3>Book {selectedClass.className}</h3>
                        <p>Date: {selectedDate?.toISOString().split('T')[0]}</p>
                        <select style={styles.select} value={selectedTime} onChange={e => setSelectedTime(e.target.value)}>
                            {['08:00','09:00','10:00','11:00','12:00','17:00','18:00','19:00','20:00'].map(t => (
                                <option key={t} value={t}>{t}</option>
                            ))}
                        </select>
                        <div style={{ marginTop: '15px' }}>
                            <button style={styles.saveBtn} onClick={confirmBooking}>Book</button>
                            <button style={styles.cancelBtn} onClick={() => setShowModal(false)}>Cancel</button>
                        </div>
                    </div>
                </>
            )}
        </div>
    )
}

const styles = {
    container: { maxWidth: '900px', margin: '30px auto', padding: '20px', backgroundColor: 'white', borderRadius: '8px', boxShadow: '0 2px 5px rgba(0,0,0,0.1)' },
    card: { border: '2px solid #ddd', borderRadius: '8px', padding: '15px', marginBottom: '10px', cursor: 'pointer', position: 'relative' },
    badge: { position: 'absolute', right: '15px', top: '15px', padding: '3px 8px', borderRadius: '4px', color: 'white', fontSize: '12px' },
    calendarSection: { marginTop: '20px' },
    message: { marginTop: '15px', padding: '10px', borderRadius: '4px' },
    overlay: { position: 'fixed', top: 0, left: 0, width: '100%', height: '100%', backgroundColor: 'rgba(0,0,0,0.5)', zIndex: 999 },
    modal: { position: 'fixed', top: '50%', left: '50%', transform: 'translate(-50%, -50%)', backgroundColor: 'white', padding: '30px', borderRadius: '8px', zIndex: 1000, minWidth: '300px' },
    select: { width: '100%', padding: '10px', border: '1px solid #ddd', borderRadius: '4px' },
    saveBtn: { padding: '10px 20px', backgroundColor: '#333', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer', marginRight: '10px' },
    cancelBtn: { padding: '10px 20px', backgroundColor: '#dc3545', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }
}

export default Dashboard