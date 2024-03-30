from django.contrib import admin
from .models import *

# Register your models here.

admin.site.register(Patient)
admin.site.register(WorkingTime)
admin.site.register(Doctor)
admin.site.register(Nurse)
admin.site.register(Companion)
admin.site.register(Apointment)