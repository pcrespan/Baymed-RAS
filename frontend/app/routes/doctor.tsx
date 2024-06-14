import { useNavigate } from "@remix-run/react"
import { MetaFunction } from "@remix-run/node"
import { useEffect, useState } from "react"

import { Badge } from "~/components/ui/badge"
import { Button } from "~/components/ui/button"
import { Card, CardContent } from "~/components/ui/card"

import { API_URL } from "~/constants/api.client"
import { Appointment } from "~/types"

export const meta: MetaFunction = () => {
  return [
    { title: "BayMed" },
    { description: "BayMed" }
  ]
}

const formatDate = date => new Intl.DateTimeFormat('en-GB', { day: '2-digit', month: '2-digit', hour: '2-digit', minute: '2-digit', hour12: false }).format(date).replace(',', '');

export default function Doctor() {
  const [appointments, setAppointments] = useState([])
  const navigate = useNavigate()

  const fetchAppointments = async () => {
    try {
      const doctorId = localStorage.getItem("doctorId")
      const response = await fetch(`${API_URL}/doctors/${doctorId}/appointments`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
      })

      if (response.ok) {
        const data = await response.json()
        setAppointments(data)
      } else {
        console.error("Failed to fetch appointments")
      }
    } catch (error) {
      console.error("Error occurred:", error)
    }
  }

  useEffect(() => {
    fetchAppointments()
  }, [])

  return (
    <div className="flex flex-col min-h-screen bg-gray-100 items-center p-4 space-y-2">
      <div className="flex flex-col items-center">
        <img src="/icon.png" alt="BayMed Logo" className="h-20 w-20 my-8" />
      </div>
      {appointments.map((appointment: Appointment) => (
        <Card className="w-full max-w-md" key={appointment.id}>
          <CardContent className="flex flex-col space-y-8">
            <div className="flex flex-col mt-4 space-y-2 -mb-2">
              <h1 className="text-lg font-bold">{appointment.patient.name}</h1>

              <div className="flex flex-col pt-2">
                <div className="flex justify-between py-2 border-b-[1px] border-gray-100">
                  <p className="text-sm font-light">Abertura do atendimento</p>
                  <p className="text-sm">{formatDate(new Date(appointment.dateTime))}</p>
                </div>
                <div className="flex justify-between py-2 border-b-[1px] border-gray-100">
                  <p className="text-sm font-light">Enfermeiro responsável</p>
                  <p className="text-sm">{appointment.nurse.name}</p>
                </div>
                <div className="flex justify-between py-2">
                  <p className="text-sm font-light">Sintomas</p>
                  <div className="flex flex-wrap justify-end gap-1">
                    {appointment.symptoms.map(symptom => (
                      <Badge key={symptom.symptomKey} variant="secondary">
                        {symptom.ptbr}
                      </Badge>
                    ))}
                  </div>
                </div>
              </div>
            </div>
            <div className="pt-1">
              <Button
                className="w-full text-white"
                onClick={() => navigate(`/appointments/${appointment.id}`)}
              >
                Ver detalhes
              </Button>
            </div>
          </CardContent>
        </Card>
      ))}
    </div>
  )
}
