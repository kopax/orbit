export class Permission {
  id: number;
  code: string;
  name: string;
  action: string;
  icon: string;
  category: number;
  description: string;
  parent: number;
  sort: number;
  children: Permission[] = [];
  createTime: Date;
  updateTime: Date;
  creator: number;
  updater: number;
  version: number;
  spread: boolean;
  state: string;
}
