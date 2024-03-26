<h2>Documentation</h2>

<u><h3>API</h3></u>

<h4>Account</h4>
Create account: POST http://localhost:8080/api/account

Body:

```json
{
  "email": "test@gmail.com",
  "password": "password",
  "firstName": "John",
  "lastName": "Jim"
}
```

Authenticate: POST http://localhost:8080/api/authenticate

Body:

```json
{
  "email": "test@gmail.com",
  "password": "password"
}
```

<h4>Pet</h4>

Create pet: POST http://localhost:8080/api/pet

Headers:

<ul>
    <li>Authorization: Bearer token</li>
</ul>

Body:

```json
{
  "name": "Buddy",
  "species": "Dog",
  "birthDate": "2021-01-01"
}
```

<h4>Appointment</h4>

Create appointment: POST http://localhost:8080/api/appointment

Headers:
<ul>
    <li>Authorization: Bearer token</li>
</ul>

Body:

```json
{
  "petId": "3ddf2488-72c4-4fe3-9412-b17732777090",
  "description": "update vaccinations",
  "appointmentStartDateTime": "2024-04-24T15:50",
  "appointmentEndDateTime": "2024-04-24T16:55"
}
```

Get appointments: GET http://localhost:8080/api/appointment/{appointmentId}

Headers:
<ul>
    <li>Authorization: Bearer token</li>
</ul>
