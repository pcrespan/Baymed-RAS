from django.db import models

# Create your models here.

# class Patient(models.Model):
#     id = models.AutoField(primary_key=True)
#     name = models.CharField(max_length=200)

class Doctor(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=200)
    specialty = models.CharField(max_length=200)

class Nurse(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=200)