// https://github.com/mxkaske/mxkaske.dev/blob/main/components/craft/fancy-multi-select.tsx

import * as React from "react"
import { X } from "lucide-react"
import { Command as CommandPrimitive } from "cmdk"

import { Badge } from "~/components/ui/badge"
import {
  Command,
  CommandGroup,
  CommandItem,
  CommandList,
} from "~/components/ui/command"

import symptomsJson from "~/constants/symptoms.json"

type Symptoms = Record<"key" | "en" | "ptbr", string>

const SYMPTOMS = symptomsJson satisfies Symptoms[]

export function SymptomsMultiSelect({ setValue }: { setValue: React.Dispatch<React.SetStateAction<Symptoms[]>> }) {
  const inputRef = React.useRef<HTMLInputElement>(null)
  const [open, setOpen] = React.useState(false)
  const [selected, setSelected] = React.useState<Symptoms[]>([])
  const [inputValue, setInputValue] = React.useState("")

  const handleUnselect = React.useCallback((symptom: Symptoms) => {
    setSelected((prev) => prev.filter((s) => s.key !== symptom.key))
  }, [])

  const handleKeyDown = React.useCallback(
    (e: React.KeyboardEvent<HTMLDivElement>) => {
      const input = inputRef.current
      if (input) {
        if (e.key === "Delete" || e.key === "Backspace") {
          if (input.value === "") {
            setSelected((prev) => {
              const newSelected = [...prev]
              newSelected.pop()
              return newSelected
            })
          }
        }
        // This is not a default behaviour of the <input /> field
        if (e.key === "Escape") {
          input.blur()
        }
      }
    },
    []
  )

  const selectables = SYMPTOMS.filter(
    (symptom) => !selected.includes(symptom)
  )

  React.useEffect(() => {
    setValue(selected)
  }, [selected, setValue])

  return (
    <Command
      onKeyDown={handleKeyDown}
      className="overflow-visible bg-transparent"
    >
      <div className="group rounded-md border border-input px-3 py-2 text-sm ring-offset-background focus-within:ring-2 focus-within:ring-ring focus-within:ring-offset-2">
        <div className="flex flex-wrap gap-1">
          {selected.map((symptom) => {
            return (
              <Badge key={symptom.key} variant="secondary">
                {symptom.ptbr}
                <button
                  className="ml-1 rounded-full outline-none ring-offset-background focus:ring-2 focus:ring-ring focus:ring-offset-2"
                  onKeyDown={(e) => {
                    if (e.key === "Enter") {
                      handleUnselect(symptom)
                    }
                  }}
                  onMouseDown={(e) => {
                    e.preventDefault()
                    e.stopPropagation()
                  }}
                  onClick={() => handleUnselect(symptom)}
                >
                  <X className="h-3 w-3 text-muted-foreground hover:text-foreground" />
                </button>
              </Badge>
            )
          })}
          {/* Avoid having the "Search" Icon */}
          <CommandPrimitive.Input
            ref={inputRef}
            value={inputValue}
            onValueChange={setInputValue}
            onBlur={() => setOpen(false)}
            onFocus={() => setOpen(true)}
            placeholder="Selecione os sintomas..."
            className="ml-2 flex-1 bg-transparent outline-none placeholder:text-muted-foreground"
          />
        </div>
      </div>
      <div className="relative mt-2">
        <CommandList>
          {open && selectables.length > 0 ? (
            <div className="absolute top-0 z-10 w-full rounded-md border bg-popover text-popover-foreground shadow-md outline-none animate-in">
              <CommandGroup className="h-full overflow-auto max-h-96">
                {selectables.map((symptom) => {
                  return (
                    <CommandItem
                      key={symptom.key}
                      onMouseDown={(e) => {
                        e.preventDefault()
                        e.stopPropagation()
                      }}
                      onSelect={() => {
                        setInputValue("")
                        setSelected((prev) => [...prev, symptom])
                      }}
                      className={"cursor-pointer"}
                    >
                      {symptom.ptbr}
                    </CommandItem>
                  )
                })}
              </CommandGroup>
            </div>
          ) : null}
        </CommandList>
      </div>
    </Command>
  )
}