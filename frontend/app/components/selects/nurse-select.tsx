import * as React from "react"
import { Check } from "lucide-react"

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

interface Nurse {
  id: number
  name: string
  corem: string
  workhours: object
}

export function NurseSelect({ setValue }: { setValue: React.Dispatch<React.SetStateAction<Nurse>> }) {
  const [open, setOpen] = React.useState(false)
  const [nurses, setNurses] = React.useState<Nurse[]>([])
  const [nurseId, setNurseId] = React.useState<number | undefined>(undefined)

  const fetchCurrentNurse = async () => {
    const response = await fetch(`${API_URL}/nurses`, {
      credentials: "include",
    })
    const data = await response.json()
    setNurses(data)
    const currentNurseUsername = localStorage.getItem("username")
    const nurse = data.find((nurse: any) => nurse?.user?.username === currentNurseUsername)
    setNurseId(nurse?.id)
    setValue({
      id: nurse?.id,
      name: nurse?.name,
      corem: nurse?.corem,
      workhours: nurse?.workhours,
    })
  }

  React.useEffect(() => {
    fetchCurrentNurse()
  }, [])

  return (
    <Popover open={open} onOpenChange={setOpen}>
      <PopoverTrigger asChild disabled>
        <Button
          variant="outline"
          role="combobox"
          aria-expanded={open}
          className="justify-between"
        >
          {nurseId
            ? nurses.find((nurse) => nurse?.id === nurseId)?.name
            : "Selecione o doutor..."}
        </Button>
      </PopoverTrigger>
      <PopoverContent className="p-0">
        <Command>
          <CommandInput placeholder="Buscar doutor..." />
          <CommandEmpty>Nenhum doutor encontrado.</CommandEmpty>
          <CommandGroup>
            <CommandList>
              {nurses.map((nurse) => (
                <CommandItem
                  key={nurse?.id}
                  value={`${nurse?.name}`}
                  onSelect={() => {
                    setNurseId(nurse?.id)
                    setOpen(false)
                  }}
                >
                  <Check
                    className={cn(
                      "mr-2 h-4 w-4",
                      nurseId === nurse?.id ? "opacity-100" : "opacity-0"
                    )}
                  />
                  {nurse?.name}
                </CommandItem>
              ))}
            </CommandList>
          </CommandGroup>
        </Command>
      </PopoverContent>
    </Popover>
  )
}
