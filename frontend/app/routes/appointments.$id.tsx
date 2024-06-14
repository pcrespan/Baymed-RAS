import { json, useLoaderData } from "@remix-run/react"
import { LoaderFunctionArgs, MetaFunction } from "@remix-run/node"
import { useEffect, useState } from "react"

import { Badge } from "~/components/ui/badge"
import { Card, CardContent } from "~/components/ui/card"

import { API_URL } from "~/constants/api.client"
import { Appointment } from "~/types"

export const meta: MetaFunction = () => {
  return [
    { title: "BayMed" },
    { description: "BayMed" }
  ]
}

export const loader = async ({ params }: LoaderFunctionArgs) => {
  return json({ appointmentId: params.id })
}

const formatDate = date => new Intl.DateTimeFormat('pt-BR', { day: '2-digit', month: '2-digit', year: 'numeric' }).format(date).replace(',', '')

const formatCPF = cpf => cpf.replace(/^(\d{3})(\d{3})(\d{3})(\d{2})$/, '$1.$2.$3-$4')

export default function AppointmentDetails() {
  const [appointment, setAppointment] = useState<Appointment>()
  const [prognostic, setPrognostic] = useState<any>()
  const { appointmentId } = useLoaderData<typeof loader>()

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
        setAppointment(data.find((appointment: Appointment) => appointment.id == appointmentId))
      } else {
        console.error("Failed to fetch appointments")
      }
    } catch (error) {
      console.error("Error occurred:", error)
    }
  }

  const fetchPrognostic = async (symptoms: string[]) => {
    try {
      const response = await fetch(`${API_URL}/prognostic`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
        body: JSON.stringify(symptoms),
      })

      if (response.ok) {
        const data = await response.json()
        setPrognostic(data)
      } else {
        console.error("Failed to fetch prognosis")
      }
    } catch (error) {
      console.error("Error occurred:", error)
    }
  }

  useEffect(() => {
    fetchAppointments()
  }, [])

  useEffect(() => {
    if (appointment) fetchPrognostic(appointment.symptoms.map(symptom => symptom.symptomKey))
  }, [appointment])

  return (
    <div className="flex flex-col min-h-screen bg-gray-100 items-center p-4 space-y-4">
      <div className="flex flex-col items-center">
        <img src="/icon.png" alt="BayMed Logo" className="h-20 w-20 my-8" />
      </div>
      {appointment && (
        <div className="w-full flex flex-1 flex-col max-w-md md:flex-row md:max-w-2xl gap-2">
          <div className="flex flex-1 flex-col gap-2">
            <Card className="w-full" key={appointment.id}>
              <CardContent className="flex flex-col space-y-8">
                <div className="flex flex-col mt-4 space-y-2 -mb-2">
                  <h1 className="text-lg font-bold">Prognóstico</h1>

                  <div className="flex flex-col">
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
                    {prognostic && (
                      <>
                        <div className="flex justify-between py-2 mt-4 border-gray-100">
                          <p className="text-sm font-light">Doença</p>
                          <p className="text-[12px]">Precisão: {prognostic[0].accuracy}%</p>
                        </div>
                        {prognostic.map((item) => (
                          <div className="flex justify-between py-2 border-t-[1px] border-gray-100" key={item.id}>
                            <p className="text-sm font-light">{item.method}</p>
                            <p className="text-sm text-right">{item.prognosis.ptbr}</p>
                          </div>
                        ))}
                      </>
                    )}
                  </div>
                </div>
              </CardContent>
            </Card>
          </div>
          <div className="flex flex-1 flex-col gap-2">
            <Card className="w-full" key={appointment.id}>
              <CardContent className="flex flex-col space-y-8">
                <div className="flex flex-col mt-4 space-y-2 -mb-2">
                  <h1 className="text-lg font-bold">Enfermeiro</h1>

                  <div className="flex flex-col">
                    <div className="flex justify-between py-2 border-b-[1px] border-gray-100">
                      <p className="text-sm font-light">Nome</p>
                      <p className="text-sm">{appointment.nurse.name}</p>
                    </div>
                    <div className="flex justify-between py-2">
                      <p className="text-sm font-light">Coren</p>
                      <p className="text-sm">{appointment.nurse.coren}</p>
                    </div>
                  </div>
                </div>
              </CardContent>
            </Card>
            <Card className="w-full" key={appointment.id}>
              <CardContent className="flex flex-col space-y-8">
                <div className="flex flex-col mt-4 space-y-2 -mb-2">
                  <h1 className="text-lg font-bold">Paciente</h1>

                  <div className="flex flex-col">
                    <div className="flex justify-between py-2 border-b-[1px] border-gray-100">
                      <p className="text-sm font-light">Nome</p>
                      <p className="text-sm">{appointment.patient.name}</p>
                    </div>
                    <div className="flex justify-between py-2 border-b-[1px] border-gray-100">
                      <p className="text-sm font-light">CPF</p>
                      <p className="text-sm">{formatCPF(String(appointment.patient.cpf))}</p>
                    </div>
                    <div className="flex justify-between py-2">
                      <p className="text-sm font-light">Data de nascimento</p>
                      <p className="text-sm">{formatDate(new Date(appointment.patient.birth))}</p>
                    </div>
                  </div>
                </div>
              </CardContent>
            </Card>
            <Card className="w-full" key={appointment.id}>
              <CardContent className="flex flex-col space-y-8">
                <div className="flex flex-col mt-4 space-y-2 -mb-2">
                  <h1 className="text-lg font-bold">Acompanhante</h1>

                  <div className="flex flex-col">
                    <div className="flex justify-between py-2 border-b-[1px] border-gray-100">
                      <p className="text-sm font-light">Nome</p>
                      <p className="text-sm">{appointment.patient.companion.name}</p>
                    </div>
                    <div className="flex justify-between py-2 border-b-[1px] border-gray-100">
                      <p className="text-sm font-light">CPF</p>
                      <p className="text-sm">{formatCPF(String(appointment.patient.companion.cpf))}</p>
                    </div>
                    <div className="flex justify-between py-2">
                      <p className="text-sm font-light">Data de nascimento</p>
                      <p className="text-sm">{formatDate(new Date(appointment.patient.companion.birth))}</p>
                    </div>
                  </div>
                </div>
              </CardContent>
            </Card>
          </div>
        </div>
      )
      }
    </div >
  )
}
