# Vaadin Web Project

This is a legacy project. I designed this site with the purpose of adding new entries/articles covering a range of topics, such as Software, gaming and film.

## Features 

- Filterable first page of web articles based upon their tags.
- Bearer Token Authenticated API that can be used to POST and upload new articles.
- Custom parser for reading text from articles into web articles. 

## Why did I make this site?

I created this website with two main objectives in mind. Firstly, this site serves as a platform for me to show and express my projects and thoughts. Secondly the creation of this site has been a project in itself. I used this site's development as an opportunity to hone my skills in full-stack development.

## Technologies
### Backend

The backend of this site is powered by Java and Springboot, a popular java framework. Springboot simplifies development by providing several key features. Springboot's auto-configuration capability automatically configures various components based on project dependencies, reducing the need for manual setup. I leveraged Springboot's auto-configuration to support specific tasks relevant to web development, data access and data security. Utilisation of Spring JPA was used to streamline access to my database layer, with MySQL serving as my database of choice. Though - when running this locally, an h2 in memory DB will be used.

### Front End

The frontend of this site is created using Vaadin, a Java framework that allows developers to build web applications while minimising the need to write in HTML, CSS and Javascript. Vaadin lets developers write UI code entirely in Java. Vaadin also integrated with Springboot, this creates a cohesive environment that makes interactions between the front and back end simple.


# How to Use
Git clone this Repo and checkout to the `sunsetting` branch - this is the branch that can be ran locally. The master branch is configured for the old url I used and the mySQL server I used to deploy.

From the root of the project, build the project using by using `mvn clean install -DskipTests` - then run the generated file within the `/target` folder
`java -jar target/webTest-0.0.1-SNAPSHOT.jar`.

On running, legacy data from the old site will be initialised onto the h2 database through hibernate. This data can be found in the `data.sql` file under `/resources`.

## API Information

All the upload API endpoints are secured by an authentication bearer token. This token has an expiry time of 1 hour.
To get a token 

### Authentication Endpoints

Base URL: `/api/v1/auth`

ADMIN USER,
username: GMW
password: ngalarza422

| Endpoint           | Method | Content-Type                 | Request Body / Params                                  | Description                                 | Response |
|-------------------|--------|------------------------------|-------------------------------------------------------|--------------------------------------------|---------|
| `/authenticate`   | POST   | `application/json`           | `{ "username": "user", "password": "pass" }`         | Authenticate a user using JSON payload.    | `200 OK` with `AuthenticationResponse` JSON object containing JWT token and user info. |
| `/authenticate`   | POST   | `application/x-www-form-urlencoded` | `username=user&password=pass`                     | Authenticate a user using form data.       | `200 OK` with `AuthenticationResponse` JSON object containing JWT token and user info. |

### Upload Endpoints

Base URL: `/api/v1/upload`

---

#### 1. Upload Text File

This upload text file is directly referenced for web articles. There is input validation for the type of file uploaded here, expects a.txt.

| Endpoint | Method | Content-Type        | Request Param | Description                                   | Response |
|----------|--------|-------------------|---------------|-----------------------------------------------|---------|
| `/text`  | POST   | `multipart/form-data` | `text` (file) | Upload a text file. Only `text/*` MIME types are allowed. | `200 OK` – Success message or `400 Bad Request` for invalid/missing file |

#### 2. Upload Image File

This is to upload images that can be referenced directly for web articles. The ID of an uploaded image is important for referencing it within a web article.

| Endpoint    | Method | Content-Type        | Request Param | Description                                                                 | Response |
|------------|--------|-------------------|---------------|-----------------------------------------------------------------------------|---------|
| `/images`  | POST   | `multipart/form-data` | `image` (file) | Upload an image file. Accepts `image/jpeg`, `image/png`, `image/gif`. Duplicate images are rejected. | `200 OK` – Success message with new ID or `400 Bad Request` for invalid/missing file or duplicate |

#### 3. Uploading a new Web Article


| Endpoint       | Method | Content-Type          | Request Params                                                                                   | Description                                                                                                     | Response |
|----------------|--------|---------------------|-------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|---------|
| `/article`     | POST   | `application/x-www-form-urlencoded` | `title` (string) – Title of the article<br>`article_text_path` (string) – Path to text file<br>`thumbnail_path` (int) – ID of uploaded image<br>`article_preview_text` (string) – Preview text<br>`tags` (list of strings) – Tags for article | Upload a new webpage article with a thumbnail, text file, preview, and tags. Title must be unique. Text file and thumbnail must exist. | `200 OK` – Success message with new WebPage ID or `400 Bad Request` for validation errors |

- `title` cannot be blank and must be unique.
- `article_text_path` must point to an existing .txt file.
- `thumbnail_path` must correspond to an existing uploaded image ID.
- `tags` that do not exist will be automatically created.

# Reflections

This was a web app I intended to use, but it was also a learning opportunity for me. There are limitations to the approach I took here,

- This approach should really use an independent storage server separate from this repo.
- There are exposed URLs / variables within this repo that would have been better read in as environment variables.
- Markdown would be much preferable to the custom parser formatting I used for .txt .

I have sunsetted this site because of these issues: I have since developed a lighter-weight for my personal portfolio here
https://gabriel-wright.github.io/Personal-website/
