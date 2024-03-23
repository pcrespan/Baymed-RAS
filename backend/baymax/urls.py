from django.urls import path
from django.urls import include
from .import views

urlpatterns = [
    path('home/', views.home, name='home'),
    path('register/', views.register, name='register'),
    path('login/', views.login, name='login'),
    path('doctors/', views.doctors, name='doctors'),
    path('nurses/', views.nurses, name='nurses'),
    path('patients/', views.patients, name='patients'),
]
