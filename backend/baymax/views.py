from django.shortcuts import render, HttpResponse

# Create your views here.

def home(request):
    return HttpResponse('This home')

def login(request):
    return HttpResponse('This is Login')

def register(request):
    return HttpResponse('This is register')

def doctors(request):
    return HttpResponse('This is Doctors')

def nurses(request):
    return HttpResponse('This is Nurses')

def patients(request):
    return HttpResponse('This is Patients')