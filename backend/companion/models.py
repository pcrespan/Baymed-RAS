from django.db import models

# Create your models here.
class Companion(models.Model):
    id = models.AutoField(primary_key=True)
    first_name = models.CharField(max_length=200)
    last_name = models.CharField(max_length=200)
    cpf = models.CharField(max_length=14) # xxx.xxx.xxx-xx
    birth = models.CharField(max_length=10) # 02/01/1997