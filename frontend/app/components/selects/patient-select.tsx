import * as React from "react"
import { Check, ChevronsUpDown } from "lucide-react"

import { cn } from "~/lib/utils"
import { Button } from "~/components/ui/button"
import {
  Command,
  CommandEmpty,
  CommandGroup,
  CommandInput,
  CommandItem,
  CommandList,
} from "~/components/ui/command"
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "~/components/ui/popover"

import { API_URL } from "~/constants/api.client"

interface Patient {
  id: number
  name: string
}

export function PatientSelect({ setValue }: { setValue: React.Dispatch<React.SetStateAction<Patient>> }) {
  const [open, setOpen] = React.useState(false)
  const [patients, setPatients] = React.useState<Patient[]>([])
  const [patientId, setPatientId] = React.useState<number | undefined>(undefined)

  const fetchPatients = async () => {
    const response = await fetch(`${API_URL}/patients`, {
      credentials: "include",
    })
    const data = await response.json()
    setPatients(data)
  }

  React.useEffect(() => {
    fetchPatients()
    console.log("fetchPatients", patients)
  }, [])

  React.useEffect(() => {
    if (!patientId) return
    const patient = patients.find((patient) => patient?.id === patientId)
    if (patient) setValue(patient)
  }, [patientId, patients, setValue])

  return (
    <Popover open={open} onOpenChange={setOpen}>
      <PopoverTrigger asChild>
        <Button
          variant="outline"
          role="combobox"
          aria-expanded={open}
          className="justify-between"
        >
          {patientId
            ? patients.find((patient) => patient?.id === patientId)?.name
            : "Selecione o paciente..."}
          <ChevronsUpDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
        </Button>
      </PopoverTrigger>
      <PopoverContent className="p-0">
        <Command>
          <CommandInput placeholder="Buscar paciente..." />
          <CommandEmpty>Nenhum paciente encontrado.</CommandEmpty>
          <CommandGroup>
            <CommandList>
              {patients.map((patient) => (
                <CommandItem
                  key={patient?.id}
                  value={`${patient?.name}`}
                  onSelect={() => {
                    setPatientId(patient?.id)
                    setOpen(false)
                  }}
                >
                  <Check
                    className={cn(
                      "mr-2 h-4 w-4",
                      patientId === patient?.id ? "opacity-100" : "opacity-0"
                    )}
                  />
                  {patient?.name}
                </CommandItem>
              ))}
            </CommandList>
          </CommandGroup>
        </Command>
      </PopoverContent>
    </Popover>
  )
}
