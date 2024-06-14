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

interface Doctor {
  id: number
  name: string
  crm: string
  specialty: string
  workhours: object
}

export function DoctorSelect({ setValue }: { setValue: React.Dispatch<React.SetStateAction<Doctor>> }) {
  const [open, setOpen] = React.useState(false)
  const [doctors, setDoctors] = React.useState<Doctor[]>([])
  const [doctorId, setDoctorId] = React.useState<number | undefined>(undefined)

  const fetchDoctors = async () => {
    const response = await fetch(`${API_URL}/doctors`, {
      credentials: "include",
    })
    const data = await response.json()
    setDoctors(data)
  }

  React.useEffect(() => {
    fetchDoctors()
    console.log("fetchDoctors", doctors)
  }, [])

  React.useEffect(() => {
    if (!doctorId) return
    const doctor = doctors.find((doctor) => doctor?.id === doctorId)
    if (doctor) setValue({
      id: doctor?.id,
      name: doctor?.name,
      crm: doctor?.crm,
      specialty: doctor?.specialty,
      workhours: doctor?.workhours,
    })
  }, [doctorId, doctors, setValue])

  return (
    <Popover open={open} onOpenChange={setOpen}>
      <PopoverTrigger asChild>
        <Button
          variant="outline"
          role="combobox"
          aria-expanded={open}
          className="justify-between"
        >
          {doctorId
            ? doctors.find((doctor) => doctor?.id === doctorId)?.name
            : "Selecione o doutor..."}
          <ChevronsUpDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
        </Button>
      </PopoverTrigger>
      <PopoverContent className="p-0">
        <Command>
          <CommandInput placeholder="Buscar doutor..." />
          <CommandEmpty>Nenhum doutor encontrado.</CommandEmpty>
          <CommandGroup>
            <CommandList>
              {doctors.map((doctor) => (
                <CommandItem
                  key={doctor?.id}
                  value={`${doctor?.name}`}
                  onSelect={() => {
                    setDoctorId(doctor?.id)
                    setOpen(false)
                  }}
                >
                  <Check
                    className={cn(
                      "mr-2 h-4 w-4",
                      doctorId === doctor?.id ? "opacity-100" : "opacity-0"
                    )}
                  />
                  {doctor?.name}
                </CommandItem>
              ))}
            </CommandList>
          </CommandGroup>
        </Command>
      </PopoverContent>
    </Popover>
  )
}
