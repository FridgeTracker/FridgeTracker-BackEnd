## API Endpoints

- POST /api/register: Create a new user.
- POST /api/login: Login using email and password for User responds with User object.
- GET /api/user/{id}: Get user by user id.
  
### Fridges

- POST /api/addFridge: Create a new fridge. (requires fridge data and user email)

### Members

- POST /api/addMember: Add member to family account

## Table of Contents

- [Fridge](#Fridge)
- [User](#User)
- [Member](#Member)
- [Item](#Item)

  
### User
| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
|  id  | [Long](#string) | optional |  |
|  name  | [string](#string) | optional |  |
|  email  | [string](#string) | optional |  |
|  password  | [string](#string) | optional |  |
|  rank  | [string](#string) | optional |  |
|  fridges  | [Fridge](#Fridge) | repeated |  |
|  members  | [Member](#Fridge) | repeated |  |


### Fridge
| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
|  id  | [Long](#string) | optional |  |
|  fridgeName  | [string](#string) | optional |  |
|  capacity  | [uint32](#int) | optional |  |
|  items  | [Item](#Item) | repeated |  |



### Member
| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
|  id  | [Long](#string) | optional |  |
|  memberName  | [string](#string) | optional |  |
|  age  | [uint32](#Int) | optional |  |
|  allergies  | [string](#string) | optional |  |


### Item
| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
|  id  | [Long](#Long) | optional |  |
|  itemName  | [string](#string) | optional |  |
|  quantity  | [uint32](#int) | optional |  |
|  expiryDate  | [string](#string) | optional |  |
|  calories  | [Long](#Long) | optional |  |
