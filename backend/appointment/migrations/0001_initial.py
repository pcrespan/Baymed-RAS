# Generated by Django 5.0.3 on 2024-04-14 17:12

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        ('doctor', '0001_initial'),
        ('nurse', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Apointment',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('date', models.DateTimeField()),
                ('doctor_id', models.ForeignKey(on_delete=django.db.models.deletion.DO_NOTHING, to='doctor.doctor')),
                ('nurse_id', models.ForeignKey(on_delete=django.db.models.deletion.DO_NOTHING, to='nurse.nurse')),
            ],
        ),
    ]