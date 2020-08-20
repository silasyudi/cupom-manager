export interface Cupom {
  id?: number;
  codigo: string;
  descricao: string;
  dataExpiracao: Date;
  dataUso?: Date;
  situacao: string;
}
