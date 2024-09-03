# Baymax-RAS (Front & Back-end)

## Description
The project helps doctors in the first contact with a patient. The main goal is to receive a prognostic of the patient's disease before the doctor actually evaluating the symptoms. This can be achieved through the use of an AI model that predicts possible diseases based on the patient's symptoms. This action can help the doctor to determine the patient's disease with more precision, and has the potential of making the appointment faster.

## Running the project

### Running with Docker - Linux
- run `docker build -t baymax-backend:latest .`
- run `docker run -p 8080:8080 baymax-backend:latest`

### Running with docker-compose - Linux
- run `docker-compose up --build -d`

### Check container status
- Check if the container is running `docker ps`
- Check container is writing logs `docker logs <container_name> -f`
