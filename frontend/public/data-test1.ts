import type { Source } from '@/types/source'

const data: Source = {
  key: 'test1',
  name: 'Test1',
  type: 'local',
  count: 3,
  atlases:
    [
      {
        id: 0,
        name: 'Test1-0',
        images: [
          {
            id: 0,
            url: '/images/1.jpg'
          }
        ]
      },
      {
        id: 1,
        name: 'Test1-1',
        images: [
          {
            id: 0,
            url: '/images/2.png'
          }
        ]
      },
      {
        id: 2,
        name: 'Test1-2',
        images: [
          {
            id: 0,
            url: '/images/3.jpg'
          }
        ]
      },
      {
        id: 3,
        name: 'Test1-3',
        images: [
          {
            id: 0,
            url: '/images/4.jpg'
          }
        ]
      },
      {
        id: 4,
        name: 'Test1-4',
        images: [
          {
            id: 0,
            url: '/images/5.jpg'
          }
        ]
      },
    ]
}

export default data
