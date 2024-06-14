import { MetaFunction } from "@remix-run/node"

import { Tabs, TabsContent, TabsList, TabsTrigger } from "~/components/ui/tabs"

import PatientForm from "~/components/patient-form"
import AppointmentForm from "~/components/appointment-form"

export const meta: MetaFunction = () => {
  return [
    { title: "BayMed" },
    { description: "BayMed" }
  ]
}

export default function Nurse() {
  return (
    <div className="flex min-h-screen bg-gray-100 justify-center items-center">
      <Tabs defaultValue="patient" className="flex flex-col m-4">
        <TabsList className="bg-gray-200">
          <TabsTrigger value="patient" className="flex-1">Paciente</TabsTrigger>
          <TabsTrigger value="appointment" className="flex-1">Atendimento</TabsTrigger>
        </TabsList>
        <TabsContent value="patient">
          <PatientForm />
        </TabsContent>
        <TabsContent value="appointment">
          <AppointmentForm />
        </TabsContent>
      </Tabs>
    </div>
  )
}
