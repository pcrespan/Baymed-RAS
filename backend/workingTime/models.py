from django.db import models

# Create your models here.
class WorkingTime(models.Model):
    id = models.AutoField(primary_key=True)
    work_start = models.TimeField()
    work_finish = models.TimeField()