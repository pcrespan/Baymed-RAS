from django.db import models
from doctor.models import Doctor
from nurse.models import Nurse

# Create your models here.
class Apointment(models.Model):
    id = models.AutoField(primary_key=True)
    doctor_id = models.ForeignKey(Doctor, on_delete=models.DO_NOTHING)
    nurse_id = models.ForeignKey(Nurse, on_delete=models.DO_NOTHING)
    date = models.DateTimeField()