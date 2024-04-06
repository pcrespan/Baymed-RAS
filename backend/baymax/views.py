from django.shortcuts import HttpResponse
from django.contrib.auth.models import User
from django.http import JsonResponse
import json
from django.views.decorators.csrf import csrf_exempt
from django.contrib.auth import authenticate

# Create your views here.

def home(request):
    return HttpResponse('This home')

@csrf_exempt
def login(request):
    if request.method == "POST":

        data = json.loads(request.body)

        username = data.get('username')
        password = data.get('password')

        user = authenticate(username=username, password=password)

        if user is not None:
            return JsonResponse({'message': 'authenticated'})
        else:
            return JsonResponse({'message': 'not authenticated'})

@csrf_exempt
def register(request):
    if request.method == "POST":
        data = json.loads(request.body)

        username = data.get('username')
        email = data.get('email')
        password = data.get('password')
        first_name = data.get('first_name')
        last_name = data.get('last_name')

        if username and email and password:
            user = User.objects.create_user(username=username, email=email, password=password, first_name=first_name, last_name=last_name)

            return JsonResponse({'message': 'Success!'}, status=201)
        else:
            return JsonResponse({'error': 'Please, insert all fields'}, status=400)
        
    else:
        return JsonResponse({'error': 'Only POST methods!'})

def doctors(request):
    return HttpResponse('This is Doctors')

def nurses(request):
    return HttpResponse('This is Nurses')

def patients(request):
    return HttpResponse('This is Patients')