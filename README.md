## API Endpoints

### Users

- POST /api/register: Create a new user.
- POST /api/login: Login using email and password for User responds with User object.
- GET /api/user/{uuid}: Get user by user id. Returns [User]{#User) Object
  
### Fridges

- POST /api/addFridge: Create a new fridge. (requires fridge data and user email)
- GET /api/getFridge/{uuid}

### Members

- POST /api/addMember: Add member to family account

### Items

****

## Table of Contents

- [Fridge](#Fridge)
- [User](#User)
- [Member](#Member)
- [Item](#Item)

  
### User
| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
|  id  | [Long](#int64) | optional |  |
|  name  | [string](#string) | optional |  |
|  email  | [string](#string) | optional |  |
|  password  | [string](#string) | optional |  |
|  rank  | [string](#string) | optional |  |
|  fridges  | [Fridge](#Fridge) | repeated |  1..* Relationship |
|  members  | [Member](#Fridge) | repeated |  1..* Relationship|


### Fridge
| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
|  id  | [Long](#int64) | optional |  |
|  fridgeName  | [string](#string) | optional |  |
|  capacity  | [int32](#int32) | optional |  |
|  items  | [Item](#Item) | repeated |  1..* Relationship |




### Member
| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
|  id  | [Long](#int64) | optional |  |
|  memberName  | [string](#string) | optional |  |
|  age  | [int32](#int32) | optional |  |
|  allergies  | [string](#string) | optional |  |


### Item
| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
|  id  | [Long](#int64) | optional |  |
|  itemName  | [string](#string) | optional |  |
|  quantity  | [int32](#int32) | optional |  |
|  expiryDate  | [string](#string) | optional |  |
|  calories  | [Long](#int64) | optional |  |
|  type  |  [string](#string)  |  optional  |  |


## Scalar Value Types

| .proto Type | Notes | C++ | Java | Python | Go | C# | PHP | Ruby |
| ----------- | ----- | --- | ---- | ------ | -- | -- | --- | ---- |
| <a name="double" /> double |  | double | double | float | float64 | double | float | Float |
| <a name="float" /> float |  | float | float | float | float32 | float | float | Float |
| <a name="int32" /> int32 | Uses variable-length encoding. | int32 | int | int | int32 | int | integer | Bignum or Fixnum (as required) |
| <a name="int64" /> Long | Uses variable-length encoding. | int64 | long | int/long | int64 | long | integer/string | Bignum |
| <a name="bool" /> bool |  | bool | boolean | boolean | bool | bool | boolean | TrueClass/FalseClass |
| <a name="string" /> string | A string must always contain UTF-8 encoded or 7-bit ASCII text. | string | String | str/unicode | string | string | string | String (UTF-8) |

