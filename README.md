# Java Study Case

ReadingIsGood APIs (Spring Boot / Hibernate / Jwt / Swagger)

## Getting Started

### Create Customer

#### Request

`POST /customer/create`

    {
        "fullname": "Kemal Şimşek",
        "email": "kemal.simsek@outlook.com",
        "password":"12345",
        "rePassword":"12345"
    }

#### Response
    {
        "message": "53e18bb2-700f-4587-b9fa-eb9014c47a18",
        "success": true
    }
    
### Login

#### Request
`POST /customer/login`

    {
        "email": "kemal.simsek@outlook.com",
        "password": "12345"
    }

#### Response
    {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1M2UxOGJiMi03MDBmLTQ1ODctYjlmYS1lYjkwMTRjNDdhMTgiLCJpYXQiOjE2MzYwNjgyMzQsImV4cCI6MTYzNjY3MzAzNH0.8Andwakr-FC9xGSgQcg48-8oP2yaT62B_8QAyYPSNTvXaVeXBgZlwKr0jF-nitjcEJGh1gIKVci2W_YufpZD4Q",
        "success": true
    }
    
### Book Create

#### Request
`POST /book/create` 
    
    {
        "name": "Book5",
        "writerName": "Writer5",
        "amount": 500,
        "stock":5
    }

#### Response
    {
        "message": "271d813d-6580-48f5-8a63-ff0f57c38ea1",
        "success": true
    }
    
### Book List

#### Request
`GET /book/list` 
    
    {
        "name": "Book5",
        "writerName": "Writer5",
        "amount": 500,
        "stock":5
    }

#### Response
    [
        {
            "id": "271d813d-6580-48f5-8a63-ff0f57c38ea1",
            "name": "Book5",
            "writerName": "Writer5",
            "amount": 500.00,
            "stock": 5
        },
        {
            "id": "6909ea31-2f3d-470c-96be-e21a5b7b0ffb",
            "name": "Book2",
            "writerName": "Writer2",
            "amount": 150.00,
            "stock": 2
        },
        {
            "id": "837e76c0-68be-4173-abbb-00f31ee2d1e0",
            "name": "Book4",
            "writerName": "Writer4",
            "amount": 300.00,
            "stock": 5
        },
        {
            "id": "bd447065-5517-4daa-834e-d152a31a5727",
            "name": "Book3",
            "writerName": "Writer3",
            "amount": 100.00,
            "stock": 3
        },
        {
            "id": "e4a1492f-0230-4dff-a7db-874b41ab9c24",
            "name": "Book1",
            "writerName": "Writer1",
            "amount": 100.00,
            "stock": 20
        }
    ]
 
 ### Book UpdateStock
 
 #### Request
 `POST /book/updateStock` 
     
     {
         "bookId":"e4a1492f-0230-4dff-a7db-874b41ab9c24",
         "stock":20
     }
 
 #### Response
     {
         "success": true
     }
     
 ### Order Create
 
 #### Request
 `POST /order/create` 
     
     {
         "bookId":"6909ea31-2f3d-470c-96be-e21a5b7b0ffb",
         "customerId":"cee4fd77-1675-4693-8255-56494922ba4c",
         "count":2
     }
 
 #### Response
     {
         "message": "03dbc965-99e1-4904-8ba0-90ccfe612ddd",
         "success": true
     }
     
 ### Order Get
 
 #### Request
 `GET /order/{id}` 
 
 #### Response
     {
         "id": "561b8146-cfdb-4161-9aec-1b2cbc90d851",
         "bookId": "6909ea31-2f3d-470c-96be-e21a5b7b0ffb",
         "customerId": "cee4fd77-1675-4693-8255-56494922ba4c",
         "count": 2,
         "amount": 300.00,
         "date": "2021-11-04T21:13:34.000+00:00"
     }

 ### Customer Order Pagination
 
 #### Request
 `GET /customer/{id}/order/{pageIndex}/{pageSize}` 
 
 #### Response
     [
         {
             "id": "03dbc965-99e1-4904-8ba0-90ccfe612ddd",
             "bookId": "6909ea31-2f3d-470c-96be-e21a5b7b0ffb",
             "customerId": "cee4fd77-1675-4693-8255-56494922ba4c",
             "count": 2,
             "amount": 300.00,
             "date": "2021-11-04T23:25:02.000+00:00"
         },
         {
             "id": "275ddddb-f08e-4370-867c-70a48cdeb707",
             "bookId": "6909ea31-2f3d-470c-96be-e21a5b7b0ffb",
             "customerId": "cee4fd77-1675-4693-8255-56494922ba4c",
             "count": 1,
             "amount": 150.00,
             "date": "2021-11-04T21:12:04.000+00:00"
         },
         {
             "id": "561b8146-cfdb-4161-9aec-1b2cbc90d851",
             "bookId": "6909ea31-2f3d-470c-96be-e21a5b7b0ffb",
             "customerId": "cee4fd77-1675-4693-8255-56494922ba4c",
             "count": 2,
             "amount": 300.00,
             "date": "2021-11-04T21:13:34.000+00:00"
         },
         {
             "id": "df61173d-a19f-4452-bd3b-6e04e1282749",
             "bookId": "6909ea31-2f3d-470c-96be-e21a5b7b0ffb",
             "customerId": "cee4fd77-1675-4693-8255-56494922ba4c",
             "count": 1,
             "amount": 150.00,
             "date": "2021-11-04T21:13:00.000+00:00"
         }
     ]
     
 ### Order List
  
  #### Request
  `GET /order/list/{from}/{to}` 
  
  #### Response
      [
          {
              "id": "03dbc965-99e1-4904-8ba0-90ccfe612ddd",
              "bookId": "6909ea31-2f3d-470c-96be-e21a5b7b0ffb",
              "customerId": "cee4fd77-1675-4693-8255-56494922ba4c",
              "count": 2,
              "amount": 300.00,
              "date": "2021-11-04T23:25:02.000+00:00"
          },
          {
              "id": "275ddddb-f08e-4370-867c-70a48cdeb707",
              "bookId": "6909ea31-2f3d-470c-96be-e21a5b7b0ffb",
              "customerId": "cee4fd77-1675-4693-8255-56494922ba4c",
              "count": 1,
              "amount": 150.00,
              "date": "2021-11-04T21:12:04.000+00:00"
          },
          {
              "id": "561b8146-cfdb-4161-9aec-1b2cbc90d851",
              "bookId": "6909ea31-2f3d-470c-96be-e21a5b7b0ffb",
              "customerId": "cee4fd77-1675-4693-8255-56494922ba4c",
              "count": 2,
              "amount": 300.00,
              "date": "2021-11-04T21:13:34.000+00:00"
          },
          {
              "id": "df61173d-a19f-4452-bd3b-6e04e1282749",
              "bookId": "6909ea31-2f3d-470c-96be-e21a5b7b0ffb",
              "customerId": "cee4fd77-1675-4693-8255-56494922ba4c",
              "count": 1,
              "amount": 150.00,
              "date": "2021-11-04T21:13:00.000+00:00"
          }
      ]
      
 ### Customer Statistics
 
 #### Request
 `GET /statistics/{customerId}` 
 
 #### Response
     [
         {
             "monthName": "November",
             "orderCount": 4,
             "bookCount": 6,
             "amount": 900.00
         }
     ]