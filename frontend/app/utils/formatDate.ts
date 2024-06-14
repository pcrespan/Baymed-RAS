export function formatDate(date: string) {
  const [day, month, year] = date.split('/')

  return `${year}-${month}-${day}`
}
