# Generated by Django 5.0.3 on 2024-04-14 18:29

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('baymax', '0010_user_professional_id'),
    ]

    operations = [
        migrations.AlterField(
            model_name='user',
            name='professional_id',
            field=models.IntegerField(null=True),
        ),
    ]