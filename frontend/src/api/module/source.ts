import api from '../index.ts'
import type { Group, SourceRequestDTO, SourceResponseDTO } from '@/types'
import type { AxiosResponse } from 'axios'

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
