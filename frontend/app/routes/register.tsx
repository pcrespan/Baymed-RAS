import { MetaFunction } from "@remix-run/node"
import { useNavigate } from "@remix-run/react"
import { useRef, useState } from "react"

import { Card, CardContent } from "~/components/ui/card"
import { Label } from "~/components/ui/label"
import { Input } from "~/components/ui/input"
import { Button } from "~/components/ui/button"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "~/components/ui/tabs"

import { API_URL } from "~/constants/api.client"

export const meta: MetaFunction = () => {
  return [
    { title: "BayMed" },
    { description: "BayMed" }
  ]
}

export default function Register() {
  const dddRef = useRef<HTMLInputElement>(null)
  const phoneRef = useRef<HTMLInputElement>(null)
  const [name, setName] = useState("")
  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")
  const [ddd, setDDD] = useState("")
  const [phone, setPhone] = useState("")
  const [id, setId] = useState("")
  const [specialty, setSpecialty] = useState("")
  const navigate = useNavigate()

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    const formData: any = {
      name,
      username,
      password,
      phone: {
        id: null,
        area: Number(ddd),
        phone,
        user: null,
      },
    };

    let endpoint;

    if (specialty) {
      formData.specialty = specialty;
      formData.crm = id;
      endpoint = "/auth/register/doctor";
    } else {
      formData.coren = id;
      endpoint = "/auth/register/nurse";
    }

    try {
      const response = await fetch(`${API_URL}${endpoint}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        navigate("/");
      } else {
        console.error("Registration failed");
      }
    } catch (error) {
      console.error("Error occurred:", error);
    }
  };

  return (
    <div className="flex min-h-screen bg-gray-100 justify-center items-center">
      <Card className="w-full max-w-sm m-4">
        <CardContent className="flex flex-col space-y-8">
          <div className="flex flex-col mt-4 space-y-2 -mb-2">
            <h1 className="text-2xl font-bold">Criar uma conta</h1>
            <p className="text-sm">Preencha os dados e selecione o tipo de conta para finalizar seu cadastro</p>
          </div>
          <form onSubmit={handleSubmit} className="w-full space-y-4">
            <div className="space-y-2">
              <Label htmlFor="name">Nome</Label>
              <Input
                id="name"
                placeholder="Digite seu nome"
                value={name}
                onChange={(e) => setName(e.target.value)}
              />
            </div>
            <div className="space-y-2">
              <Label htmlFor="ddd">Número de celular</Label>
              <div className="flex space-x-2">
                <Input
                  ref={dddRef}
                  id="ddd"
                  placeholder="99"
                  type="text"
                  pattern="\d*"
                  maxLength={2}
                  value={ddd}
                  onChange={(e) => {
                    setDDD(e.target.value)
                    if (e.target.value.length === 2) phoneRef.current?.focus()
                  }}
                  style={{ width: "44px" }}
                />
                <Input
                  ref={phoneRef}
                  id="phone"
                  placeholder="999999999"
                  type="text"
                  pattern="\d*"
                  value={phone}
                  onChange={(e) => setPhone(e.target.value)}
                  onKeyDown={(e) => {
                    if (!phone && e.key === 'Backspace') dddRef.current?.focus()
                  }}
                />
              </div>
            </div>
            <div className="space-y-2">
              <Label htmlFor="username">Apelido</Label>
              <Input
                id="username"
                placeholder="Digite seu apelido"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>
            <div className="space-y-2">
              <Label htmlFor="password">Senha</Label>
              <Input
                id="password"
                placeholder="Digite sua senha"
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
            <Tabs defaultValue="nurse" className="flex flex-col" onValueChange={(value) => {
              if (value === "nurse") setSpecialty("")
              else setSpecialty("SURGEON")
              setId("")
            }}>
              <TabsList>
                <TabsTrigger value="nurse" className="flex-1">Enfermeiro</TabsTrigger>
                <TabsTrigger value="doctor" className="flex-1">Médico</TabsTrigger>
              </TabsList>
              <TabsContent value="nurse">
                <div className="space-y-2">
                  <Label htmlFor="id">Coren</Label>
                  <Input
                    id="id"
                    placeholder="Digite seu Coren"
                    value={id}
                    onChange={(e) => setId(e.target.value)}
                  />
                </div>
              </TabsContent>
              <TabsContent value="doctor">
                <div className="space-y-4">
                  <div className="space-y-2">
                    <Label htmlFor="id">CRM</Label>
                    <Input
                      id="id"
                      placeholder="Digite seu CRM"
                      value={id}
                      onChange={(e) => setId(e.target.value)}
                    />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="specialty">Especialidade</Label>
                    <Input
                      disabled
                      id="specialty"
                      placeholder="Digite sua especialidade"
                      value={specialty}
                      onChange={(e) => setSpecialty(e.target.value)}
                    />
                  </div>
                </div>
              </TabsContent>
            </Tabs>

            <div className="pt-4">
              <Button type="submit" className="w-full text-white">Cadastrar</Button>
            </div>
          </form>
        </CardContent>
      </Card>
    </div>
  )
}
