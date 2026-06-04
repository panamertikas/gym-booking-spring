# GymBooking React Frontend

A gym booking management system frontend built with React and Vite.

## Technologies
- React 19
- Vite
- React Router DOM
- React DatePicker

## Prerequisites
- Node.js 18+
- GymBooking Spring Boot backend running on port 8080

## Setup

1. Clone the repository:
```bash
git clone https://github.com/panamertikas/gym-booking-react.git
cd gym-booking-react
```

2. Install dependencies:
```bash
npm install
```

3. Run the development server:
```bash
npm run dev
```

4. Open `http://localhost:5173`

## Default Users
- Admin: `admin` / `admin123`
- User: register via the login page

## Pages

### Admin
- `/admin/members` - Members management
- `/admin/gym-classes` - Gym Classes management
- `/admin/bookings` - All bookings
- `/admin/register-member` - Register new member

### User
- `/dashboard` - Available classes with calendar booking
- `/my-bookings` - My bookings
- `/profile` - My profile

## Related Projects
- [GymBooking Spring Boot API](https://github.com/panamertikas/gym-booking-spring)