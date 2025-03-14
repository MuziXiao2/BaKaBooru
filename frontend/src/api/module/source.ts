import api from '../index.ts'
import type { AxiosResponse } from 'axios'
import type { SourceResponseDTO, SourceRequestDTO, Group } from '@/types'

export function createGroups(name: string) {
  return api.post('/groups', { name })
}

export function addSource(source: SourceRequestDTO) {
  return api.post('/sources', source)
}

export function getGroups(): Promise<AxiosResponse<Group[]>> {
  return api.get(`/groups`)
}

export function getSources(sourceGroup: Group): Promise<AxiosResponse<SourceResponseDTO[]>> {
  return api.get(`/sources?groupId=${sourceGroup.id}`)
}
