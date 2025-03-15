import type { AxiosResponse } from 'axios'
import type { Group, SourceRequestDTO, SourceResponseDTO } from '@/types'
import api from '@/api/index.ts'

export function createGroup(name: string) {
  return api.post(
    '',
    { name: name },
    {
      params: { type: 'group' },
    },
  )
}

export function getGroups(): Promise<AxiosResponse<Group[]>> {
  return api.get('', {
    params: {
      type: 'group',
    },
  })
}

export function addSource(source: SourceRequestDTO) {
  return api.post('', source, {
    params: {
      type: 'source',
    },
  })
}

export function getSources(group: Group): Promise<AxiosResponse<SourceResponseDTO[]>> {
  return api.get('', {
    params: {
      type: 'source',
      group_id: group.id,
    },
  })
}
