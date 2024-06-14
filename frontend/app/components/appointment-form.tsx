import { useState } from "react"

import { Button } from "~/components/ui/button"
import { Card, CardContent } from "~/components/ui/card"
import { Label } from "~/components/ui/label"
import { SymptomsMultiSelect } from "~/components/selects/symptoms-multi-select"
import { PatientSelect } from "~/components/selects/patient-select"
import { DoctorSelect } from "~/components/selects/doctor-select"
import { NurseSelect } from "~/components/selects/nurse-select"

import { API_URL } from "~/constants/api.client"

export default function AppointmentForm() {
  const [nurse, setNurse] = useState({})
  const [doctor, setDoctor] = useState({})
  const [patient, setPatient] = useState({})
  const [symptoms, setSymptoms] = useState([{}])

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault()

    try {
      const body = JSON.stringify({
        dateTime: new Date().toISOString(),
        status: "PENDING",
        symptoms: symptoms.map((symptom) => ({
          ...symptom,
          symptomKey: symptom.key
        })),
        patient,
        doctor,
        nurse,
      })

      const response = await fetch(`${API_URL}/appointments`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
        body,
      })

      if (response.ok) {
        alert("Atendimento cadastrado com sucesso")
        window.location.reload()
      } else {
        console.error("Registration failed")
      }
    } catch (error) {
      console.error("Error occurred:", error)
    }
  }

  return (
    <Card className="w-full max-w-sm">
      <CardContent className="flex flex-col space-y-8">
        <div className="flex flex-col mt-4 space-y-2 -mb-2">
          <h1 className="text-2xl font-bold">Criar atendimento</h1>
          <p className="text-sm">Informe os dados do paciente e do acompanhante do paciente</p>
        </div>
        <form onSubmit={handleSubmit} className="flex flex-col w-full space-y-4">
          <div className="flex flex-col space-y-2">
            <Label>Sintomas</Label>
            <SymptomsMultiSelect setValue={setSymptoms} />
          </div>

          <div className="flex flex-col space-y-2">
            <Label>Paciente</Label>
            <PatientSelect setValue={setPatient} />
          </div>

          <div className="flex flex-col space-y-2">
            <Label>Doutor</Label>
            <DoctorSelect setValue={setDoctor} />
          </div>

          <div className="flex flex-col space-y-2">
            <Label>Enfermeiro</Label>
            <NurseSelect setValue={setNurse} />
          </div>

          <div className="pt-4">
            <Button type="submit" className="w-full text-white">Cadastrar</Button>
          </div>
        </form>
      </CardContent>
    </Card>
  )
}
