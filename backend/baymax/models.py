from django.db import models

# Create your models here.

class Patient(models.Model):
    id = models.AutoField(primary_key=True)
    first_name = models.CharField(max_length=200)
    last_name = models.CharField(max_length=200)
    cpf = models.CharField(max_length=14) # xxx.xxx.xxx-xx
    birth = models.CharField(max_length=10) # 02/01/1997

class WorkingTime(models.Model):
    id = models.AutoField(primary_key=True)
    work_start = models.TimeField()
    work_finish = models.TimeField()

class Doctor(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=200)
    specialty = models.CharField(max_length=200)
    crm = models.CharField(max_length=200)
    work_hours = models.ForeignKey(WorkingTime, on_delete=models.DO_NOTHING)

class Nurse(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=200)
    work_hours = models.ForeignKey(WorkingTime, on_delete=models.DO_NOTHING)

class Companion(models.Model):
    id = models.AutoField(primary_key=True)
    first_name = models.CharField(max_length=200)
    last_name = models.CharField(max_length=200)
    cpf = models.CharField(max_length=14) # xxx.xxx.xxx-xx
    birth = models.CharField(max_length=10) # 02/01/1997

class Apointment(models.Model):
    id = models.AutoField(primary_key=True)
    doctor_id = models.ForeignKey(Doctor, on_delete=models.DO_NOTHING)
    nurse_id = models.ForeignKey(Nurse, on_delete=models.DO_NOTHING)
    date = models.DateTimeField()