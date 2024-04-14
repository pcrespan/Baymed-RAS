from django.db import models
from workingTime.models import WorkingTime
from django.contrib.auth.models import User

# Create your models here.

class Doctor(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE, related_name='doctor_profile')
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=200)
    specialty = models.CharField(max_length=200)
    crm = models.CharField(max_length=200)
    work_hours = models.ForeignKey(WorkingTime, on_delete=models.DO_NOTHING)
