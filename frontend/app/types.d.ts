export interface Phone {
  id: number;
  area: number;
  phone: string;
}

export interface Authority {
  authority: string;
}

export interface User {
  id: number;
  username: string;
  password: string;
  profession: string;
  phone: Phone;
  enabled: boolean;
  authorities: Authority[];
  accountNonExpired: boolean;
  accountNonLocked: boolean;
  credentialsNonExpired: boolean;
}

export interface Doctor {
  id: number;
  name: string;
  crm: string;
  specialty: string;
  workHours: string | null;
  user: User;
}

export interface Nurse {
  id: number;
  name: string;
  workHours: string | null;
  coren: string;
  user: User;
}

export interface Companion {
  id: number;
  name: string;
  cpf: number;
  birth: string;
}

export interface Patient {
  id: number;
  name: string;
  cpf: number;
  birth: string;
  companion: Companion;
}

export interface Symptom {
  id: number;
  en: string;
  symptomKey: string;
  ptbr: string;
}

export interface Appointment {
  id: number;
  doctor: Doctor;
  nurse: Nurse;
  patient: Patient;
  dateTime: string;
  symptoms: Symptom[];
  status: string;
}