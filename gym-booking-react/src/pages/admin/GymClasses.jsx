import { useEffect, useState } from "react"
import Navbar from "../../components/Navbar"

function GymClasses() {
    const [gymClasses, setGymClasses] = useState([])
    const [className, setClassName] = useState('')
    const [trainer, setTrainer] = useState('')
    const [maxCapacity, setMaxCapacity] = useState('')
    const [showModal, setShowModal] = useState(false)
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

    async function addGymClass() {
        const response = await fetch('/api/gym_classes', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify({ className, trainer, maxCapacity: parseInt(maxCapacity) })
        })
        if (response.ok) {
            setClassName('')
            setTrainer('')
            setMaxCapacity('')
            setShowModal(false)
            loadGymClasses()
        } else {
            const msg = await response.text()
            alert(msg)
        }
    }

    async function deleteGymClass(id) {
        if (!confirm('Are you sure?')) return
        const response = await fetch(`/api/gym_classes/${id}`, {
            method: 'DELETE',
            headers: { 'Authorization': 'Bearer ' + token }
        })
        if (response.ok) {
            loadGymClasses()
        } else {
            const msg = await response.text()
            alert(msg)
        }
    }

    return (
        <div>
            <Navbar role={role} />
            <div style={styles.container}>
                <h1>Gym Classes</h1>
                <table style={styles.table}>
                    <thead>
                        <tr>
                            <th style={styles.th}>ID</th>
                            <th style={styles.th}>Class Name</th>
                            <th style={styles.th}>Trainer</th>
                            <th style={styles.th}>Current Capacity</th>
                            <th style={styles.th}>Max Capacity</th>
                            <th style={styles.th}>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {gymClasses.map(gymClass => (
                            <tr key={gymClass.id}>
                                <td style={styles.td}>{gymClass.id}</td>
                                <td style={styles.td}>{gymClass.className}</td>
                                <td style={styles.td}>{gymClass.trainer}</td>
                                <td style={styles.td}>{gymClass.currentCapacity}</td>
                                <td style={styles.td}>{gymClass.maxCapacity}</td>
                                <td style={styles.td}>
                                    <button style={styles.deleteBtn} onClick={() => deleteGymClass(gymClass.id)}>Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>

                <button style={styles.addBtn} onClick={() => setShowModal(true)}>+</button>
            </div>

            {showModal && (
                <>
                    <div style={styles.overlay} onClick={() => setShowModal(false)} />
                    <div style={styles.modal}>
                        <h2>Add Gym Class</h2>
                        <input style={styles.input} placeholder="Class Name" value={className} onChange={e => setClassName(e.target.value)} />
                        <input style={styles.input} placeholder="Trainer" value={trainer} onChange={e => setTrainer(e.target.value)} />
                        <input style={styles.input} placeholder="Max Capacity" type="number" value={maxCapacity} onChange={e => setMaxCapacity(e.target.value)} />
                        <div style={{ marginTop: '15px' }}>
                            <button style={styles.saveBtn} onClick={addGymClass}>Save</button>
                            <button style={styles.cancelBtn} onClick={() => setShowModal(false)}>Cancel</button>
                        </div>
                    </div>
                </>
            )}
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
    deleteBtn: { padding: '6px 12px', backgroundColor: '#dc3545', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' },
    addBtn: { marginTop: '20px', width: '50px', height: '50px', borderRadius: '50%', backgroundColor: '#333', color: 'white', fontSize: '24px', border: 'none', cursor: 'pointer' },
    overlay: { position: 'fixed', top: 0, left: 0, width: '100%', height: '100%', backgroundColor: 'rgba(0,0,0,0.5)', zIndex: 999 },
    modal: { position: 'fixed', top: '50%', left: '50%', transform: 'translate(-50%, -50%)', backgroundColor: 'white', padding: '30px', borderRadius: '8px', zIndex: 1000, minWidth: '300px' },
    input: { display: 'block', width: '100%', padding: '10px', marginBottom: '10px', border: '1px solid #ddd', borderRadius: '4px', boxSizing: 'border-box' },
    saveBtn: { padding: '10px 20px', backgroundColor: '#333', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer', marginRight: '10px' },
    cancelBtn: { padding: '10px 20px', backgroundColor: '#dc3545', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }
}

export default GymClasses