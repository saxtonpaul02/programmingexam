package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static chess.ChessGame.TeamColor.BLACK;
import static chess.ChessGame.TeamColor.WHITE;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor pieceColor;
    private final PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> moves = new ArrayList<>();
        ChessPiece piece = board.getPiece(myPosition);
        if (piece.getPieceType() == PieceType.KING) {
            int row = myPosition.getRow();
            int column = myPosition.getColumn();
            row++;
            if (row <= 8) {
                kingAndKnightMoves(board, myPosition, moves, piece, row, column);
            }
            column++;
            if (row <= 8 && column <= 8) {
                kingAndKnightMoves(board, myPosition, moves, piece, row, column);
            }
            row--;
            if (column <= 8) {
                kingAndKnightMoves(board, myPosition, moves, piece, row, column);
            }
            row--;
            if (row >= 1 && column <= 8) {
                kingAndKnightMoves(board, myPosition, moves, piece, row, column);
            }
            column--;
            if (row >= 1) {
                kingAndKnightMoves(board, myPosition, moves, piece, row, column);
            }
            column--;
            if (row >= 1 && column >= 1) {
                kingAndKnightMoves(board, myPosition, moves, piece, row, column);
            }
            row++;
            if (column >= 1) {
                kingAndKnightMoves(board, myPosition, moves, piece, row, column);
            }
            row++;
            if (row <= 8 && column >= 1) {
                kingAndKnightMoves(board, myPosition, moves, piece, row, column);
            }
        }

        else if (piece.getPieceType() == PieceType.KNIGHT) {
            int row = myPosition.getRow();
            int column = myPosition.getColumn();
            row++;
            row++;
            column++;
            if (row <= 8 && column <= 8) {
                kingAndKnightMoves(board, myPosition, moves, piece, row, column);
            }
            row--;
            column++;
            if (row <= 8 && column <= 8) {
                kingAndKnightMoves(board, myPosition, moves, piece, row, column);
            }
            row--;
            row--;
            if (row >= 1 && column <= 8) {
                kingAndKnightMoves(board, myPosition, moves, piece, row, column);
            }
            row--;
            column--;
            if (row >= 1 && column <= 8) {
                kingAndKnightMoves(board, myPosition, moves, piece, row, column);
            }
            column--;
            column--;
            if (row >= 1 && column >= 1) {
                kingAndKnightMoves(board, myPosition, moves, piece, row, column);
            }
            row++;
            column--;
            if (row >= 1 && column >= 1) {
                kingAndKnightMoves(board, myPosition, moves, piece, row, column);
            }
            row++;
            row++;
            if (row <= 8 && column >= 1) {
                kingAndKnightMoves(board, myPosition, moves, piece, row, column);
            }
            row++;
            column++;
            if (row <= 8 && column >= 1) {
                kingAndKnightMoves(board, myPosition, moves, piece, row, column);
            }
        }

        else if (piece.getPieceType() == PieceType.QUEEN) {
            int row1 = myPosition.getRow();
            int column1 = myPosition.getColumn();
            while (row1 < 8) {
                row1++;
                if (queenRookAndBishopMoves(board, myPosition, moves, piece, row1, column1)) {
                    break;
                }
            }
            int row2 = myPosition.getRow();
            int column2 = myPosition.getColumn();
            while (row2 < 8 && column2 < 8) {
                row2++;
                column2++;
                if (queenRookAndBishopMoves(board, myPosition, moves, piece, row2, column2)) {
                    break;
                }
            }
            int row3 = myPosition.getRow();
            int column3 = myPosition.getColumn();
            while (column3 < 8) {
                column3++;
                if (queenRookAndBishopMoves(board, myPosition, moves, piece, row3, column3)) {
                    break;
                }
            }
            int row4 = myPosition.getRow();
            int column4 = myPosition.getColumn();
            while (row4 > 1 && column4 < 8) {
                row4--;
                column4++;
                if (queenRookAndBishopMoves(board, myPosition, moves, piece, row4, column4)) {
                    break;
                }
            }
            int row5 = myPosition.getRow();
            int column5 = myPosition.getColumn();
            while (row5 > 1) {
                row5--;
                if (queenRookAndBishopMoves(board, myPosition, moves, piece, row5, column5)) {
                    break;
                }
            }
            int row6 = myPosition.getRow();
            int column6 = myPosition.getColumn();
            while (row6 > 1 && column6 > 1) {
                row6--;
                column6--;
                if (queenRookAndBishopMoves(board, myPosition, moves, piece, row6, column6)) {
                    break;
                }
            }
            int row7 = myPosition.getRow();
            int column7 = myPosition.getColumn();
            while (column7 > 1) {
                column7--;
                if (queenRookAndBishopMoves(board, myPosition, moves, piece, row7, column7)) {
                    break;
                }
            }
            int row8 = myPosition.getRow();
            int column8 = myPosition.getColumn();
            while (row8 < 8 && column8 > 1) {
                row8++;
                column8--;
                if (queenRookAndBishopMoves(board, myPosition, moves, piece, row8, column8)) {
                    break;
                }
            }
        }

        else if (piece.getPieceType() == PieceType.ROOK) {
            int row1 = myPosition.getRow();
            int column1 = myPosition.getColumn();
            while (row1 < 8) {
                row1++;
                if (queenRookAndBishopMoves(board, myPosition, moves, piece, row1, column1)) {
                    break;
                }
            }
            int row2 = myPosition.getRow();
            int column2 = myPosition.getColumn();
            while (column2 < 8) {
                column2++;
                if (queenRookAndBishopMoves(board, myPosition, moves, piece, row2, column2)) {
                    break;
                }
            }
            int row3 = myPosition.getRow();
            int column3 = myPosition.getColumn();
            while (row3 > 1) {
                row3--;
                if (queenRookAndBishopMoves(board, myPosition, moves, piece, row3, column3)) {
                    break;
                }
            }
            int row4 = myPosition.getRow();
            int column4 = myPosition.getColumn();
            while (column4 > 1) {
                column4--;
                if (queenRookAndBishopMoves(board, myPosition, moves, piece, row4, column4)) {
                    break;
                }
            }
        }

        else if (piece.getPieceType() == PieceType.BISHOP) {
            int row1 = myPosition.getRow();
            int column1 = myPosition.getColumn();
            while (row1 < 8 && column1 < 8) {
                row1++;
                column1++;
                if (queenRookAndBishopMoves(board, myPosition, moves, piece, row1, column1)) {
                    break;
                }
            }
            int row2 = myPosition.getRow();
            int column2 = myPosition.getColumn();
            while (row2 > 1 && column2 < 8) {
                row2--;
                column2++;
                if (queenRookAndBishopMoves(board, myPosition, moves, piece, row2, column2)) {
                    break;
                }
            }
            int row3 = myPosition.getRow();
            int column3 = myPosition.getColumn();
            while (row3 > 1 && column3 > 1) {
                row3--;
                column3--;
                if (queenRookAndBishopMoves(board, myPosition, moves, piece, row3, column3)) {
                    break;
                }
            }
            int row4 = myPosition.getRow();
            int column4 = myPosition.getColumn();
            while (row4 < 8 && column4 > 1) {
                row4++;
                column4--;
                if (queenRookAndBishopMoves(board, myPosition, moves, piece, row4, column4)) {
                    break;
                }
            }
        }

        else if (piece.getPieceType() == PieceType.PAWN) {
            int row = myPosition.getRow();
            int column = myPosition.getColumn();
            if (piece.getTeamColor() == WHITE) {
                row++;
                ChessPosition newPosition = new ChessPosition(row, column);
                ChessPosition newPositionLeft = new ChessPosition(row, column-1);
                ChessPosition newPositionRight = new ChessPosition(row, column+1);
                ChessPosition newPositionForward = new ChessPosition(row+1, column);
                if (board.getPiece(newPosition) == null) {
                    if (row == 8) {
                        ChessMove move1 = new ChessMove(myPosition, newPosition, PieceType.QUEEN);
                        moves.add(move1);
                        ChessMove move2 = new ChessMove(myPosition, newPosition, PieceType.ROOK);
                        moves.add(move2);
                        ChessMove move3 = new ChessMove(myPosition, newPosition, PieceType.BISHOP);
                        moves.add(move3);
                        ChessMove move4 = new ChessMove(myPosition, newPosition, PieceType.KNIGHT);
                        moves.add(move4);
                    } else {
                        ChessMove move1 = new ChessMove(myPosition, newPosition, null);
                        moves.add(move1);
                        if (row == 3) {
                            if (board.getPiece(newPositionForward) == null) {
                                ChessMove move2 = new ChessMove(myPosition, newPositionForward, null);
                                moves.add(move2);
                            }
                        }
                    }
                }
                if (column - 1 >= 1) {
                    if (board.getPiece(newPositionLeft) != null) {
                        if (board.getPiece(newPositionLeft).getTeamColor() == BLACK) {
                            if (row == 8) {
                                ChessMove move5 = new ChessMove(myPosition, newPositionLeft, PieceType.QUEEN);
                                moves.add(move5);
                                ChessMove move6 = new ChessMove(myPosition, newPositionLeft, PieceType.ROOK);
                                moves.add(move6);
                                ChessMove move7 = new ChessMove(myPosition, newPositionLeft, PieceType.BISHOP);
                                moves.add(move7);
                                ChessMove move8 = new ChessMove(myPosition, newPositionLeft, PieceType.KNIGHT);
                                moves.add(move8);
                            } else {
                                ChessMove move = new ChessMove(myPosition, newPositionLeft, null);
                                moves.add(move);
                            }
                        }
                    }
                }
                if (column + 1 <= 8) {
                    if (board.getPiece(newPositionRight) != null) {
                        if (board.getPiece(newPositionRight).getTeamColor() == BLACK) {
                            if (row == 8) {
                                ChessMove move9 = new ChessMove(myPosition, newPositionRight, PieceType.QUEEN);
                                moves.add(move9);
                                ChessMove move10 = new ChessMove(myPosition, newPositionRight, PieceType.ROOK);
                                moves.add(move10);
                                ChessMove move11 = new ChessMove(myPosition, newPositionRight, PieceType.BISHOP);
                                moves.add(move11);
                                ChessMove move12 = new ChessMove(myPosition, newPositionRight, PieceType.KNIGHT);
                                moves.add(move12);
                            } else {
                                ChessMove move = new ChessMove(myPosition, newPositionRight, null);
                                moves.add(move);
                            }
                        }
                    }
                }
            } else {
                row--;
                ChessPosition newPosition = new ChessPosition(row, column);
                ChessPosition newPositionLeft = new ChessPosition(row, column+1);
                ChessPosition newPositionRight = new ChessPosition(row, column-1);
                ChessPosition newPositionForward = new ChessPosition(row-1, column);
                if (board.getPiece(newPosition) == null) {
                    if (row == 1) {
                        ChessMove move1 = new ChessMove(myPosition, newPosition, PieceType.QUEEN);
                        moves.add(move1);
                        ChessMove move2 = new ChessMove(myPosition, newPosition, PieceType.ROOK);
                        moves.add(move2);
                        ChessMove move3 = new ChessMove(myPosition, newPosition, PieceType.BISHOP);
                        moves.add(move3);
                        ChessMove move4 = new ChessMove(myPosition, newPosition, PieceType.KNIGHT);
                        moves.add(move4);
                    } else {
                        ChessMove move1 = new ChessMove(myPosition, newPosition, null);
                        moves.add(move1);
                        if (row == 6) {
                            if (board.getPiece(newPositionForward) == null) {
                                ChessMove move2 = new ChessMove(myPosition, newPositionForward, null);
                                moves.add(move2);
                            }
                        }
                    }
                }
                if (column + 1 <= 8) {
                    if (board.getPiece(newPositionLeft) != null) {
                        if (board.getPiece(newPositionLeft).getTeamColor() == WHITE) {
                            if (row == 1) {
                                ChessMove move5 = new ChessMove(myPosition, newPositionLeft, PieceType.QUEEN);
                                moves.add(move5);
                                ChessMove move6 = new ChessMove(myPosition, newPositionLeft, PieceType.ROOK);
                                moves.add(move6);
                                ChessMove move7 = new ChessMove(myPosition, newPositionLeft, PieceType.BISHOP);
                                moves.add(move7);
                                ChessMove move8 = new ChessMove(myPosition, newPositionLeft, PieceType.KNIGHT);
                                moves.add(move8);
                            } else {
                                ChessMove move = new ChessMove(myPosition, newPositionLeft, null);
                                moves.add(move);
                            }
                        }
                    }
                }
                if (column - 1 >= 1) {
                    if (board.getPiece(newPositionRight) != null) {
                        if (board.getPiece(newPositionRight).getTeamColor() == WHITE) {
                            if (row == 1) {
                                ChessMove move9 = new ChessMove(myPosition, newPositionRight, PieceType.QUEEN);
                                moves.add(move9);
                                ChessMove move10 = new ChessMove(myPosition, newPositionRight, PieceType.ROOK);
                                moves.add(move10);
                                ChessMove move11 = new ChessMove(myPosition, newPositionRight, PieceType.BISHOP);
                                moves.add(move11);
                                ChessMove move12 = new ChessMove(myPosition, newPositionRight, PieceType.KNIGHT);
                                moves.add(move12);
                            } else {
                                ChessMove move = new ChessMove(myPosition, newPositionRight, null);
                                moves.add(move);
                            }
                        }
                    }
                }
            }
        }
        return moves;
    }

    private boolean queenRookAndBishopMoves(ChessBoard board, ChessPosition myPosition, ArrayList<ChessMove> moves, ChessPiece piece, int row, int column) {
        ChessPosition newPosition = new ChessPosition(row, column);
        if (board.getPiece(newPosition) == null) {
            ChessMove move = new ChessMove(myPosition, newPosition, null);
            moves.add(move);
        } else {
            if (board.getPiece(newPosition).getTeamColor() != piece.getTeamColor()) {
                ChessMove move = new ChessMove(myPosition, newPosition, null);
                moves.add(move);
            }
            return true;
        }
        return false;
    }

    private void kingAndKnightMoves(ChessBoard board, ChessPosition myPosition, ArrayList<ChessMove> moves, ChessPiece piece, int row, int column) {
        ChessPosition newPosition = new ChessPosition(row, column);
        if (board.getPiece(newPosition) != null) {
            if (board.getPiece(newPosition).getTeamColor() != piece.getTeamColor()) {
                ChessMove move = new ChessMove(myPosition, newPosition, null);
                moves.add(move);
            }
        } else {
            ChessMove move = new ChessMove(myPosition, newPosition, null);
            moves.add(move);
        }
    }
}
