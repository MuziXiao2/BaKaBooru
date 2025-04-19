import type { SourceReferenceDTO } from '@/types'
import api from '@/api/index.ts'

const url = 'http://localhost:8080/api'

export async function createGroup(name: string) {
  const response = await api.post(
    url,
    { name: name },
    {
      params: { type: 'group' },
    },
  )
  return response.data.data
}

export async function getGroups() {
  const response = await api.get(url, {
    params: {
      type: 'group',
    },
  })
  return response.data.data
}

export async function addSource(source: SourceReferenceDTO) {
  const response = await api.post(url, source, {
    params: {
      type: 'source',
      action: 'add',
    },
  })
  return response.data.data
}

export async function getSources() {
  const response = await api.get(url, {
    params: {
      type: 'source',
    },
  })
  return response.data.data
}
