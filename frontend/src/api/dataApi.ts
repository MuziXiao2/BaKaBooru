import type { AxiosResponse } from 'axios'
import type { Group, SourceReferenceDTO, SourceResponseDTO } from '@/types'
import api from '@/api/index.ts'

const url = 'http://localhost:8080/api'

export function createGroup(name: string) {
  return api.post(
    url,
    { name: name },
    {
      params: { type: 'group' },
    },
  )
}

export function getGroups(): Promise<AxiosResponse<Group[]>> {
  return api.get(url, {
    params: {
      type: 'group',
    },
  })
}

export function addSource(source: SourceReferenceDTO) {
  return api.post(url, source, {
    params: {
      type: 'source',
      action: 'add',
    },
  })
}

export function getSources(group: Group): Promise<AxiosResponse<SourceResponseDTO[]>> {
  return api.get(url, {
    params: {
      type: 'source',
      group_id: group.id,
    },
  })
}
