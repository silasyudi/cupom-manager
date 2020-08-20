export interface Cupom {
  id?: number;
  codigo: string;
  descricao: string;
  dataCadastro: Date;
  dataExpiracao: Date;
  dataUso?: Date;
  situacao: string;
}
