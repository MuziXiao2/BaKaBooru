import type { Source } from '@/types/source'

const data: Source = {
  name: 'test1',
  type: 'local',
  atlases:
    [
      {
        id: 0,
        name: 'test1-0',
        images: [
          {
            id: 0,
            url: '/images/1.jpg'
          }
        ]
      },
      {
        id: 1,
        name: 'test1-1',
        images: [
          {
            id: 0,
            url: '/images/2.png'
          }
        ]
      },
      {
        id: 2,
        name: 'test1-2',
        images: [
          {
            id: 0,
            url: '/images/3.jpg'
          }
        ]
      },
      {
        id: 3,
        name: 'test1-3',
        images: [
          {
            id: 0,
            url: '/images/4.jpg'
          }
        ]
      },
      {
        id: 4,
        name: 'test1-4',
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
