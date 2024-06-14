import { MetaFunction } from "@remix-run/node"
import { Link, useNavigate } from "@remix-run/react"
import { useState } from "react"

import { Card, CardContent } from "~/components/ui/card"
import { Label } from "~/components/ui/label"
import { Input } from "~/components/ui/input"
import { Button } from "~/components/ui/button"

import { API_URL } from "~/constants/api.client"

export const meta: MetaFunction = () => {
  return [
    { title: "BayMed" },
    { description: "BayMed" }
  ]
}

export default function Index() {
  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")
  const navigate = useNavigate()

  const fetchCurrentDoctorId = async () => {
    try {
      const response = await fetch(`${API_URL}/doctors`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include",
      })

      if (response.ok) {
        const data = await response.json()
        const currentDoctorUsername = localStorage.getItem("username")
        const doctor = data.find((nurse: any) => nurse?.user?.username === currentDoctorUsername)
        localStorage.setItem("doctorId", doctor?.id)
      } else {
        console.error("Failed to fetch doctors")
      }
    } catch (error) {
      console.error("Error occurred:", error)
    }
  }

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault()

    const formData = new FormData()
    formData.append("username", username)
    formData.append("password", password)

    try {
      const response = await fetch(`${API_URL}/auth/login`, {
        method: "POST",
        credentials: "include",
        body: formData,
      })

      if (response.ok) {
        const data = await response.json()
        const role = data[0]?.authority
        localStorage.setItem("username", username)
        localStorage.setItem("role", role)
        if (role === "ROLE_NURSE") return navigate("/nurse")
        if (role === "ROLE_DOCTOR") {
          await fetchCurrentDoctorId()
          return navigate("/doctor")
        }
        throw new Error("Invalid role")
      } else {
        throw new Error("Login failed")
      }
    } catch (error) {
      localStorage.clear()
      console.error(error)
    }
  }

  return (
    <div className="flex min-h-screen bg-gray-100 justify-center items-center">
      <Card className="w-full max-w-sm m-4">
        <CardContent className="flex flex-col items-center space-y-8">
          <div className="flex flex-col items-center">
            <img src="/icon.png" alt="BayMed Logo" className="h-20 w-20 mt-4" />
            <h1 className="text-2xl font-bold">BayMed</h1>
          </div>
          <form onSubmit={handleSubmit} className="w-full space-y-4">
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
            <div className="flex flex-col items-center pt-4 space-y-4">
              <Button type="submit" className="w-full text-white">Entrar</Button>
              <Link to="/register" className="text-primary hover:underline">
                Criar uma conta
              </Link>
            </div>
          </form>
        </CardContent>
      </Card>
    </div>
  )
}
