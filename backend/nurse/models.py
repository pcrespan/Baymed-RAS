from django.db import models
from workingTime.models import WorkingTime

# Create your models here.
class Nurse(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=200)
    work_hours = models.ForeignKey(WorkingTime, on_delete=models.DO_NOTHING)